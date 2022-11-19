package de.rumeotech.minelin.networking.packet.impl.side.clientbound.status

import com.google.gson.Gson
import de.rumeotech.minelin.configuration.ConfigurationManager
import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.*
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter
import de.rumeotech.minelin.networking.util.models.request.StatusResponseModel
@PacketInfo(id = 0x00, state = PacketState.STATUS, boundTo = PacketBound.CLIENTBOUND)
class SPacketStatusResponse(private val status: StatusResponseModel) : Packet() {

    override fun read(reader: PacketReader, client: MinelinClient) {
    }

    override fun write(writer: PacketWriter) {
        writer.writeString(ConfigurationManager.gson.toJson(status))
    }
}