package de.rumeotech.minelin.networking.packet.impl

import de.rumeotech.minelin.networking.util.DatatypeReader
import java.io.InputStream

class PacketReader(private val input: InputStream) {

    /**
     * This function will read a var int of the input stream
     */
    fun readVarInt(): Int {
        return DatatypeReader.readVarInt(input)
    }

    /**
     * This function will read the next string of the input stream, the minecraft protocol first sends a VARINT with LENGTH
     */
    fun readString(): String {
        val length = this.readVarInt()
        val bytes = ByteArray(length)
        input.read(bytes)
        return String(bytes)
    }
}