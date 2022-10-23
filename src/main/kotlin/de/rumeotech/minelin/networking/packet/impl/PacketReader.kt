package de.rumeotech.minelin.networking.packet.impl

import de.rumeotech.minelin.networking.util.DatatypeReader
import de.rumeotech.minelin.networking.util.datatype.VarInt
import java.io.InputStream

class PacketReader(private val input: InputStream) {

    /**
     * This function will read a var int of the input stream
     */
    fun readVarInt(): VarInt {
        return DatatypeReader.readVarInt(input)
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