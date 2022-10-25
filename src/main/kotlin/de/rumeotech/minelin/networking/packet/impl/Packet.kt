package de.rumeotech.minelin.networking.packet.impl

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter
import de.rumeotech.minelin.networking.util.datatype.VarInt
import org.apache.logging.log4j.LogManager

abstract class Packet {

    val LOGGER = LogManager.getLogger()

    abstract fun read(reader: PacketReader, client: MinelinClient)
    abstract fun write(writer: PacketWriter)

    fun getPacketId(): Int {
        return javaClass.getAnnotation(PacketInfo::class.java).id
    }
}