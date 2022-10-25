package de.rumeotech.minelin.networking.packet.impl.util

import de.rumeotech.minelin.networking.util.VariableHelper
import de.rumeotech.minelin.networking.util.datatype.VarInt
import java.io.InputStream

class PacketReader(val input: InputStream) {

    /**
     * This function will read a var int of the input stream
     */
    fun readVarInt(): VarInt {
        return VariableHelper.readVarInt(input)
    }

    fun readLong(): Long {
        val bytes = ByteArray(Long.SIZE_BYTES)
        input.read(bytes)
        return ((bytes[0].toLong() and 0xFF) shl 56) or
                ((bytes[1].toLong() and 0xFF) shl 46) or
                ((bytes[2].toLong() and 0xFF) shl 40) or
                ((bytes[3].toLong() and 0xFF) shl 32) or
                ((bytes[4].toLong() and 0xFF) shl 24) or
                ((bytes[5].toLong() and 0xFF) shl 16) or
                ((bytes[6].toLong() and 0xFF) shl 8) or
                ((bytes[7].toLong() and 0xFF) shl 0)
    }

    /**
     * This function will read the next string of the input stream, the minecraft protocol first sends a VARINT with LENGTH
     */
    fun readString(): String {
        val length = this.readVarInt()
        val bytes = ByteArray(length.value)
        input.read(bytes)
        return String(bytes)
    }

    fun readUShort(): UShort {
        val bytes = ByteArray(2)
        input.read(bytes)
        val short = ((bytes[0].toInt() and 0xFF) shl 8) or ((bytes[1].toInt() and 0xFF) shl 0)
        return short.toUShort()
    }
}