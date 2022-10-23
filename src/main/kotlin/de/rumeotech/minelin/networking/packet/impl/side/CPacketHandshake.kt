package de.rumeotech.minelin.networking.packet.impl.side

import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.PacketReader

@PacketInfo(id = 0x00)
class CPacketHandshake : Packet() {

    override fun read(reader: PacketReader) {
        val protocol = reader.readVarInt()
        val address = reader.readString()
        LOGGER.info("Receiving handshake - protocol: $protocol, server: $address")
    }

    override fun write() {
    }

}