package de.rumeotech.minelin.networking.packet.impl.side.serverbound

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.packet.impl.PacketState
import de.rumeotech.minelin.networking.packet.impl.side.clientbound.SPacketStatusResponse
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter

@PacketInfo(id = 0x00, state = PacketState.STATUS)
class CPacketStatusRequest : Packet() {

    override fun read(reader: PacketReader, client: MinelinClient) {
        client.sendPacket(SPacketStatusResponse())
    }

    override fun write(writer: PacketWriter) {
    }

}