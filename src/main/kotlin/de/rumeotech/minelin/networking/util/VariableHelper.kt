package de.rumeotech.minelin.networking.util

import de.rumeotech.minelin.networking.util.datatype.VarInt
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


object VariableHelper {

    fun readVarInt(stream: InputStream): VarInt {
        var position = 0
        var value = 0
        var read = 0

        do {
            read = stream.read()
            val v = read and 0x7F
            value = value or (v shl (position * 7))

            position++
            if(position > 5) throw IOException("VarInt is too big")
        } while((read and 0x80) != 0)
        return VarInt(value, (position).toByte())
    }

    fun writeVarInt(stream: OutputStream, varInt: VarInt) {
        do {
            var v = varInt.value and 0x7F

            varInt.value = varInt.value ushr 7
            if(varInt.value != 0) {
                v = v or 0x80
            }
            stream.write(v)
        } while(varInt.value != 0)
    }

}