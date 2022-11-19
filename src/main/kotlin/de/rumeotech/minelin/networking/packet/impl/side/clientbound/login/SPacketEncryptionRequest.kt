package de.rumeotech.minelin.networking.packet.impl.side.clientbound.login

import de.rumeotech.minelin.Minelin
import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketBound
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.PacketState
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter

@PacketInfo(id = 0x01, state = PacketState.LOGIN, boundTo = PacketBound.CLIENTBOUND)
class SPacketEncryptionRequest(private val verifyToken: ByteArray) : Packet() {

    override fun read(reader: PacketReader, client: MinelinClient) {
    }

    override fun write(writer: PacketWriter) {
        // server id
        writer.writeString("")
        // public key
        writer.writeBytes(Minelin.keyPair.public.encoded)
        // verify token
        writer.writeBytes(verifyToken)
    }
}