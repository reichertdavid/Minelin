package de.rumeotech.minelin.networking.packet.impl.util

import de.rumeotech.minelin.configuration.ConfigurationManager
import de.rumeotech.minelin.networking.util.VariableHelper
import de.rumeotech.minelin.networking.util.datatype.VarInt
import de.rumeotech.minelin.networking.util.models.chat.ChatObject
import java.io.ByteArrayOutputStream

class PacketWriter(val output: ByteArrayOutputStream) {

    fun writeChat(chat: ChatObject) {
        val string = ConfigurationManager.gson.toJson(chat)
        this.writeString(string)
    }

    fun writeVarInt(varInt: VarInt) {
        VariableHelper.writeVarInt(output, varInt)
    }

    fun writeString(string: String) {
        writeVarInt(VarInt(string.length, 0))
        output.write(string.encodeToByteArray())
    }

    fun writeLong(value: Long) {
        val bytes = ByteArray(Long.SIZE_BYTES)
        bytes.indices.forEach { i ->
            bytes[i] = ((value shr ((8-i)*Long.SIZE_BYTES)) and 0xFF).toByte()
        }
        output.write(bytes)
    }

}
