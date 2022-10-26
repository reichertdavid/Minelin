package de.rumeotech.minelin.networking.packet

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.*
import de.rumeotech.minelin.networking.packet.impl.side.serverbound.handshake.CPacketHandshake
import de.rumeotech.minelin.networking.packet.impl.side.serverbound.status.CPacketPingRequest
import de.rumeotech.minelin.networking.packet.impl.side.serverbound.status.CPacketStatusRequest
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.util.VariableHelper
import org.apache.logging.log4j.LogManager
import java.io.ByteArrayInputStream
import java.lang.Exception


class PacketHandler {

    private val LOGGER = LogManager.getLogger()

    /**
     * A list of all packets that could be sent by the client
     */
    private val incomingPackets = mutableListOf(
        CPacketHandshake(), CPacketStatusRequest(), CPacketPingRequest())

    fun handlePacket(client: MinelinClient) {
        try {
            val input = client.inputStream

            if(input.available() == 0) return

            // Update that our client sent something
            client.lastInputAt = System.currentTimeMillis()

            val packetSize = VariableHelper.readVarInt(input)
            assert(packetSize.value >= 0)

            val packetId = VariableHelper.readVarInt(input)

            val data = ByteArray(packetSize.value - packetId.size)
            if(packetSize.value - packetId.size > 0) {
                input.read(data)
            }


            LOGGER.info("receiving packet - id: $packetId, size: $packetSize, state: ${client.currentState}")

            val packetStream = ByteArrayInputStream(data)
            val packet = incomingPackets.firstOrNull { p ->
                val annotation = p.javaClass.getAnnotation(PacketInfo::class.java)
                annotation.id == packetId.value && annotation.state == client.currentState && annotation.boundTo == PacketBound.SERVERBOUND
            }

            if(packet != null) {
                packet.read(PacketReader(packetStream), client)
            } else {
                LOGGER.warn("cannot find packet [id: $packetId, size: $packetSize]")
            }

        } catch (e: Exception) {
            LOGGER.warn("disconnecting client ${e.message}", e)
            client.disconnect()
        }
    }

}