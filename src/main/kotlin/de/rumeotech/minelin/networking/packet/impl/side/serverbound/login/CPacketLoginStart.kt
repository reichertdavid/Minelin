package de.rumeotech.minelin.networking.packet.impl.side.serverbound.login

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.PacketState
import de.rumeotech.minelin.networking.packet.impl.side.clientbound.login.SPacketEncryptionRequest
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter
import de.rumeotech.minelin.networking.util.datatype.VarInt
import java.util.*

@PacketInfo(id = 0x00, state = PacketState.LOGIN)
class CPacketLoginStart : Packet() {

    override fun read(reader: PacketReader, client: MinelinClient) {
        val username = reader.readString()
        if (username.length > 16) {
            LOGGER.error("username of player: \"$username\" is too long, disconnecting")
            client.disconnect()
        }
        val hasSigData =                        reader.readBoolean()
        val timestamp =         if (hasSigData) reader.readLong()                       else -1L
        val publicKeyLength =   if (hasSigData) reader.readVarInt()                     else VarInt(-1, -1)
        val publicKey =         if (hasSigData) reader.readBytes(publicKeyLength.value) else ByteArray(0)
        val sigLength =         if (hasSigData) reader.readVarInt()                     else VarInt(-1, -1)
        val sigData =           if (hasSigData) reader.readBytes(sigLength.value)       else ByteArray(0)
        val hasUUID =                           reader.readBoolean()
        val uuid =              if (hasUUID)    reader.readUUID()                       else UUID.randomUUID()
        LOGGER.info("player: \"$username\" tries to login [uuid: $uuid, timestamp: $timestamp]")
        client.sendPacket(SPacketEncryptionRequest(client.verifyToken))
    }

    override fun write(writer: PacketWriter) {
    }

}