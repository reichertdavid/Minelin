package de.rumeotech.minelin.networking.packet.impl

import de.rumeotech.minelin.networking.MinelinClient
import org.apache.logging.log4j.LogManager

abstract class Packet {

    val LOGGER = LogManager.getLogger()

    abstract fun read(reader: PacketReader, client: MinelinClient)
    abstract fun write()

}