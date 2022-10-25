package de.rumeotech.minelin.networking.packet.impl.side.serverbound

import de.rumeotech.minelin.configuration.ConfigurationManager
import de.rumeotech.minelin.networking.MinelinClient
import de.rumeotech.minelin.networking.packet.impl.Packet
import de.rumeotech.minelin.networking.packet.impl.PacketInfo
import de.rumeotech.minelin.networking.packet.impl.util.PacketReader
import de.rumeotech.minelin.networking.packet.impl.PacketState
import de.rumeotech.minelin.networking.packet.impl.side.clientbound.SPacketStatusResponse
import de.rumeotech.minelin.networking.packet.impl.util.PacketWriter
import de.rumeotech.minelin.networking.util.models.request.StatusResponseModel
import de.rumeotech.minelin.networking.util.models.request.impl.SRDescriptionModel
import de.rumeotech.minelin.networking.util.models.request.impl.SRPlayersModel
import de.rumeotech.minelin.networking.util.models.request.impl.SRVersionDataModel

@PacketInfo(id = 0x00, state = PacketState.STATUS)
class CPacketStatusRequest : Packet() {

    override fun read(reader: PacketReader, client: MinelinClient) {
        val status = StatusResponseModel(
            version = SRVersionDataModel("1.19.2", 760),
            players = SRPlayersModel(ConfigurationManager.serverConfiguration.maxPlayers, 0, arrayOf()),
            description = SRDescriptionModel(ConfigurationManager.serverConfiguration.motd),
            favicon = "",
            previewsChat = false,
            enforcesSecureChat = true
        )
        client.sendPacket(SPacketStatusResponse(status))
    }

    override fun write(writer: PacketWriter) {
    }

}