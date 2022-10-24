package de.rumeotech.minelin.networking.util

import de.rumeotech.minelin.networking.util.datatype.VarInt
import java.io.EOFException
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.atomic.AtomicInteger
import kotlin.experimental.and


object DatatypeReader {

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

}