package de.rumeotech.minelin.networking.packet.impl.side

import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.PacketReader
import de.rumeotech.minelin.networking.packet.impl.PacketState

/**
 * https://wiki.vg/Protocol#Handshake
 */
@PacketInfo(id = 0x00)
class CPacketHandshake : Packet() {

    override fun read(reader: PacketReader) {
        val protocol = reader.readVarInt()
        val address = reader.readString()
        val port = reader.readUShort()
        val state = PacketState.values().first { it.id == reader.readVarInt() }
    }

    override fun write() {
    }

}