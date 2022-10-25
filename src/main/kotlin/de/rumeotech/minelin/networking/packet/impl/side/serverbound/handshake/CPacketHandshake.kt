package de.rumeotech.minelin.networking.packet.impl.side.serverbound.handshake

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.packet.impl.PacketState
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter

/**
 * https://wiki.vg/Protocol#Handshake
 */
@PacketInfo(id = 0x00, state = PacketState.HANDSHAKING)
class CPacketHandshake : Packet() {

    override fun read(reader: PacketReader, client: MinelinClient) {
        val protocol = reader.readVarInt()
        val address = reader.readString()
        val port = reader.readUShort()
        val nextState = reader.readVarInt().value
        val state = PacketState.values().first { it.id ==  nextState }

        client.currentState = state
    }

    override fun write(writer: PacketWriter) {
    }

}