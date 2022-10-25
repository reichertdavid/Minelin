package de.rumeotech.minelin.networking.util.models.request

import de.rumeotech.minelin.networking.util.models.request.impl.SRDescriptionModel
import de.rumeotech.minelin.networking.util.models.request.impl.SRPlayersModel
import de.rumeotech.minelin.networking.util.models.request.impl.SRVersionDataModel

data class StatusResponseModel(
    val version: SRVersionDataModel,
    val players: SRPlayersModel,
    val description: SRDescriptionModel,
    val favicon: String,
    val previewsChat: Boolean,
    val enforcesSecureChat: Boolean
)

