package de.rumeotech.minelin.networking.packet.impl.side.clientbound

import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.*
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter
import de.rumeotech.minelin.networking.util.models.request.StatusResponseModel
import de.rumeotech.minelin.networking.util.models.request.impl.SRDescriptionModel
import de.rumeotech.minelin.networking.util.models.request.impl.SRPlayersModel
import de.rumeotech.minelin.networking.util.models.request.impl.SRVersionDataModel
import org.json.JSONObject

@PacketInfo(id = 0x00, state = PacketState.STATUS, boundTo = PacketBound.CLIENTBOUND)
class SPacketStatusResponse : Packet() {

    override fun read(reader: PacketReader, client: MinelinClient) {
    }

    override fun write(writer: PacketWriter) {
        val model = StatusResponseModel(SRVersionDataModel("1.19.2", 760), SRPlayersModel(100, 0, arrayOf()), SRDescriptionModel("Welcome to Minelin"), "",
            previewsChat = false,
            enforcesSecureChat = false
        )
        val responseJSON = JSONObject(model)
        writer.writeString(responseJSON.toString())
    }
}