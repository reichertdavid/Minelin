package de.rumeotech.minelin.networking.packet.impl

import org.apache.logging.log4j.LogManager

abstract class Packet {

    val LOGGER = LogManager.getLogger()

    abstract fun read(reader: PacketReader)
    abstract fun write()

}