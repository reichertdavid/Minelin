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

        while(true) {
            read = stream.read()
            val v = read and 0x7F
            value = value or (v shl (position * 7))


            if((read and 0x80) == 0) break

            position++
            if(position > 5) throw IOException("VarInt is too big")
        }
        return VarInt(value, (position + 1).toByte())
    }

}