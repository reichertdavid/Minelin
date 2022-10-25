package de.rumeotech.minelin.networking.packet.impl.util

import de.rumeotech.minelin.networking.util.VariableHelper
import de.rumeotech.minelin.networking.util.datatype.VarInt
import java.io.ByteArrayOutputStream

class PacketWriter(val output: ByteArrayOutputStream) {

    fun writeVarInt(varInt: VarInt) {
        VariableHelper.writeVarInt(output, varInt)
    }

    fun writeString(string: String) {
        writeVarInt(VarInt(string.length, 0))
        output.write(string.encodeToByteArray())
    }

}
