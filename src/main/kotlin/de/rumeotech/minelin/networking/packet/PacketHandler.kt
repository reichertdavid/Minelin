package de.rumeotech.minelin.networking.packet

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.*
import de.rumeotech.minelin.networking.packet.impl.side.serverbound.CPacketHandshake
import de.rumeotech.minelin.networking.packet.impl.side.serverbound.CPacketStatusRequest
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.util.VariableHelper
import org.apache.logging.log4j.LogManager
import java.io.ByteArrayInputStream
import java.lang.Exception


class PacketHandler {

    private val LOGGER = LogManager.getLogger()
    private val packetList = mutableListOf<Packet>(CPacketHandshake(), CPacketStatusRequest())

    fun handlePacket(client: MinelinClient) {
        try {
            val input = client.inputStream

            val packetSize = VariableHelper.readVarInt(input)
            assert(packetSize.value >= 0)

            val packetId = VariableHelper.readVarInt(input)

            val data = ByteArray(packetSize.value - packetId.size)
            if(packetSize.value - packetId.size > 0) {
                input.read(data)
            }


            LOGGER.info("receiving packet - id: $packetId, size: $packetSize, state: ${client.currentState}")

            val packetStream = ByteArrayInputStream(data)
            val packet = packetList.firstOrNull { p ->
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