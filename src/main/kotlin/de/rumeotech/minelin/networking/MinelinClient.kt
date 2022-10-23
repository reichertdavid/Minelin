package de.rumeotech.minelin.networking

import com.sun.java.browser.net.ProxyService
import de.rumeotech.minelin.Minelin
import java.net.Socket
import java.util.*


class MinelinClient(val socket: Socket) {

    val id = UUID.randomUUID()
    val inputStream = socket.getInputStream()
    val outputStream = socket.getOutputStream()

    init {
        val clientThread = Thread(
            {

                while (this.isConnected()) {
                    Minelin.server.packetHandler.handlePacket(this)
                }
            }, "Client #${socket.inetAddress.hostName}"
        )
        clientThread.start()
    }

    /**
     * Checks if the client is connected by using some simple socket checks
     */
    fun isConnected(): Boolean {
        return this.socket.isConnected && !this.socket.isClosed && !this.socket.isInputShutdown && !this.socket.isOutputShutdown
    }

    /**
     * This method shuts down the connection from both client safely
     */
    fun disconnect() {
        Minelin.server.connectedClients.removeIf {
            it.id == id
        }
        if (!socket.isInputShutdown) inputStream.close()
        if (!socket.isOutputShutdown) outputStream.close()
        if (socket.isConnected) socket.close()
    }

}