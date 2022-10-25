package de.rumeotech.minelin.networking.packet.impl.side.clientbound.status

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketBound
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.PacketState
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter

@PacketInfo(id = 0x01, state = PacketState.STATUS, boundTo = PacketBound.CLIENTBOUND)
class SPacketPongResponse(private val payload: Long) : Packet() {

    override fun read(reader: PacketReader, client: MinelinClient) {
    }

    override fun write(writer: PacketWriter) {
        writer.writeLong(payload)
    }

}