package de.rumeotech.minelin.networking.packet.impl.side.serverbound

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.PacketReader
import de.rumeotech.minelin.networking.packet.impl.PacketState
import java.util.logging.Logger

/**
 * https://wiki.vg/Protocol#Handshake
 */
@PacketInfo(id = 0x00, state = PacketState.HANDSHAKING)
class CPacketHandshake : Packet() {

    override fun read(reader: PacketReader, client: MinelinClient) {
        val protocol = reader.readVarInt()
        val address = reader.readString()
        val port = reader.readUShort()
        val state = PacketState.values().first { it.id == reader.readVarInt().value }

        client.currentState = state
        LOGGER.info("Handshake received! protocol: $protocol, host: $address, port: $port, state: $state")
    }

    override fun write() {
    }

}