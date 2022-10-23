package de.rumeotech.minelin.networking.packet

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.PacketReader
import de.rumeotech.minelin.networking.packet.impl.side.CPacketHandshake
import de.rumeotech.minelin.networking.util.DatatypeReader
import org.apache.logging.log4j.LogManager
import java.io.ByteArrayInputStream
import java.io.IOException
import java.util.concurrent.atomic.AtomicInteger


class PacketHandler {

    private val LOGGER = LogManager.getLogger()
    private val packetList = mutableListOf<Packet>(CPacketHandshake())

    fun handlePacket(client: MinelinClient) {
        try {
            val input = client.inputStream

            val packetSize = DatatypeReader.readVarInt(input)

            // stole this from old project, credits given to @paulschwahn
            // TODO: revision if better code is available
            val packetData = ByteArray(packetSize)
            var n = 0
            while (n < packetSize) {
                val size: Int = input.read(packetData, n, packetSize - n)
                if (size < 0) throw IOException()
                n += size
            }

            val pointer = AtomicInteger(0)
            val packetId: Int = DatatypeReader.readVarInt(packetData, pointer)
            val strippedData = ByteArray(packetSize - pointer.get())
            System.arraycopy(packetData, pointer.get(), strippedData, 0, strippedData.size)

            packetList.first { p -> p.javaClass.getAnnotation(PacketInfo::class.java).id == packetId }.read(PacketReader(ByteArrayInputStream(strippedData)))

            LOGGER.info("Receiving packet $packetId with size $packetSize")
        } catch (e: IOException) {
            LOGGER.warn("Disconnected client reason: connection lost due [${e.message}]")
            client.disconnect()
        }
    }

}