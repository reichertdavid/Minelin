package de.rumeotech.minelin.networking.packet

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.PacketReader
import de.rumeotech.minelin.networking.packet.impl.PacketState
import de.rumeotech.minelin.networking.packet.impl.side.serverbound.CPacketHandshake
import de.rumeotech.minelin.networking.util.DatatypeReader
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.ByteArrayInputStream
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.atomic.AtomicInteger


class PacketHandler {

    private val LOGGER = LogManager.getLogger()
    private val packetList = mutableListOf<Packet>(CPacketHandshake())

    fun handlePacket(client: MinelinClient) {
        try {
            val input = client.inputStream

            val packetSize = DatatypeReader.readVarInt(input)
            LOGGER.info("packet was announced - size: $packetSize")
            assert(packetSize.value >= 0)

            val packetId = DatatypeReader.readVarInt(input)

            val data = ByteArray(packetSize.value - packetId.size)
            if(packetSize.value - packetId.size > 0) {
                input.read(data)
            }


            LOGGER.info("receiving packet - id: $packetId, size: $packetSize, state: ${client.currentState}")

            val packetStream = ByteArrayInputStream(data)
            val packet = packetList.firstOrNull { p ->
                val annotation = p.javaClass.getAnnotation(PacketInfo::class.java)
                annotation.id == packetId.value && annotation.state == client.currentState
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