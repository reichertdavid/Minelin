package de.rumeotech.minelin.networking

import de.rumeotech.minelin.Minelin
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketState
import de.rumeotech.minelin.networking.packet.impl.side.clientbound.status.SPacketPongResponse
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter
import de.rumeotech.minelin.networking.util.VariableHelper
import de.rumeotech.minelin.networking.util.datatype.VarInt
import java.io.ByteArrayOutputStream
import java.net.Socket
import java.security.SecureRandom
import java.util.*


class MinelinClient(val socket: Socket) {

    /**
     * Each client gets a random UUID to manage connections
     */
    val id = UUID.randomUUID()

    /**
     * create final object of clients input stream
     */
    val inputStream = socket.getInputStream()!!

    /**
     * create final object of clients output stream
     */
    private val outputStream = socket.getOutputStream()

    /**
     * This value determines in which state the connection currently is
     */
    var currentState = PacketState.HANDSHAKING

    /**
     * If the client is quiet for more than this amount of milliseconds, we'll disconnect the client
     */
    private val timeoutAfter = 1000 * 15

    /**
     * This will determine how long the last input from our client is back in time
     */
    var lastInputAt = System.currentTimeMillis()

    /**
     * Array of random 4 bytes that verifies the client
     */
    val verifyToken = ByteArray(4)

    /**
     * Secured random for clients verify token
     */
    private val random = SecureRandom()

    init {
        // Generate verify token for client
        random.nextBytes(verifyToken)

        val clientThread = Thread(
            {

                while (this.isConnected()) {
                    Minelin.server.packetHandler.handlePacket(this)
                }
                this.disconnect()
            }, "Client #${socket.inetAddress.hostName}"
        )
        clientThread.start()
    }

    /**
     * This method send a packet in the right order, the correct order of the protocol is:
     *
     * LENGTH   - VARINT        - (Length of packet data + id)get
     * ID       - VARINT        - (the id of the given packet)
     * DATA     - BYTE ARRAY    - (the data of the packet)
     */
    fun sendPacket(packet: Packet) {
        // Write packet data to temporary output stream
        val temp = ByteArrayOutputStream()
        packet.write(PacketWriter(temp))

        // create varint object of the id
        val id = VarInt(packet.getPacketId(), 1)
        id.updateSize()

        /// Write length of the packet data + length of the packet id
        VariableHelper.writeVarInt(outputStream, VarInt(temp.size() + id.size, 0))

        // Write the packet id to the output stream
        VariableHelper.writeVarInt(outputStream, id)

        // Write the packet data to the output stream
        outputStream.write(temp.toByteArray())

        // send the data to the client
        outputStream.flush()

        if(packet is SPacketPongResponse) {
            // Client will be disconnected safely after 3 seconds
            this.lastInputAt = System.currentTimeMillis() - (1000*12)
        }
    }

    /**
     * Checks if the client is connected by using some simple socket checks
     */
    fun isConnected(): Boolean {
        if(System.currentTimeMillis() - lastInputAt > timeoutAfter) return false

        return this.socket.isConnected && !this.socket.isClosed && !this.socket.isInputShutdown && !this.socket.isOutputShutdown
    }

    /**
     * This method shuts down the connection from both client safely
     */
    fun disconnect() {
        Minelin.server.connectedClients.removeIf {
            it.id.toString() == this.id.toString()
        }
        if (!socket.isInputShutdown) inputStream.close()
        if (!socket.isOutputShutdown) outputStream.close()
        if (socket.isConnected) socket.close()
    }

}