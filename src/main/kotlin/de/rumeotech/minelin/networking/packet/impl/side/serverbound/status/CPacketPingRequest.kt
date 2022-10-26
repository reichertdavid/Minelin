package de.rumeotech.minelin.networking.packet.impl.side.serverbound.status

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.PacketState
import de.rumeotech.minelin.networking.packet.impl.side.clientbound.status.SPacketPongResponse
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter

@PacketInfo(id = 0x01, state = PacketState.STATUS)
class CPacketPingRequest : Packet() {

    override fun read(reader: PacketReader, client: MinelinClient) {
        val payload = reader.readLong()
        client.sendPacket(SPacketPongResponse(payload))
    }

    override fun write(writer: PacketWriter) {
    }


}