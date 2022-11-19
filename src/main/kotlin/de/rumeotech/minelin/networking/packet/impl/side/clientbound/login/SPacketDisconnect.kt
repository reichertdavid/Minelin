package de.rumeotech.minelin.networking.packet.impl.side.clientbound.login

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketBound
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.PacketState
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter
import de.rumeotech.minelin.networking.util.models.chat.ChatObject

@PacketInfo(id = 0x00, state = PacketState.LOGIN, boundTo = PacketBound.CLIENTBOUND)
class SPacketDisconnect(private val reason: ChatObject) : Packet(){

    override fun read(reader: PacketReader, client: MinelinClient) {
    }

    override fun write(writer: PacketWriter) {
        writer.writeChat(reason)
    }

}
