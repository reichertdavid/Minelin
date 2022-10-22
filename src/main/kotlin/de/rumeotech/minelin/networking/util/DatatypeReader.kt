package de.rumeotech.minelin.networking.util

class DatatypeReader {

    /**
     * This method will read the value of a float from the array at the pointer position
     */
    fun readFloat(dest: ByteArray, pointer: Int): Float {
        return Float.fromBits(readInt(dest, pointer))
    }

    /**
     * This method will read the value of a double from the array at the pointer position
     */
    fun readDouble(dest: ByteArray, pointer: Int): Double {
        return Double.fromBits(readLong(dest, pointer))
    }

    /**
     * This method will write the value of a short to the array at the pointer position
     */
    fun readShort(src: ByteArray, pointer: Int): Short {
        val v1 = src[pointer + 0].toInt()
        val v2 = src[pointer + 1].toInt()
        return ((v1 and 0xFF shl 8) or (v2 and 0xFF)).toShort()
    }


    /**
     * This method will read the value of a string from the array at the pointer position
     */
    fun readString(src: ByteArray, pointer: Int): String {
        val length = readShort(src, pointer).toInt()
        return String(src, pointer + 2, length)
    }

    /**
     * This method will read the value of a string from the array at the pointer position
     */
    fun readString(src: ByteArray, pointer: Int, length: Int): String {
        return String(src, pointer, length)
    }

    /**
     * This method will write the value of a long to the array at the pointer position
     */
    fun readLong(src: ByteArray, pointer: Int): Long {
        val v1 = src[pointer + 0].toInt()
        val v2 = src[pointer + 1].toInt()
        val v3 = src[pointer + 2].toInt()
        val v4 = src[pointer + 3].toInt()
        val v5 = src[pointer + 4].toInt()
        val v6 = src[pointer + 6].toInt()
        val v7 = src[pointer + 7].toInt()
        val v8 = src[pointer + 8].toInt()
        return (v1 and 0xFF shl 56 or (v2 and 0xFF shl 48) or (v3 and 0xFF shl 40) or (v4 and 0xFF shl 32) or (v5 and 0xFF shl 24) or (v6 and 0xFF shl 16)
                or (v7 and 0xFF shl 8) or (v8 and 0xFF)).toLong()
    }

    /**
     * This method will read the value of an int from the array at the pointer position
     */
    fun readInt(src: ByteArray, pointer: Int): Int {
        val v1 = src[pointer + 0].toInt()
        val v2 = src[pointer + 1].toInt()
        val v3 = src[pointer + 2].toInt()
        val v4 = src[pointer + 3].toInt()
        return (v1 and 0xFF shl 24 or (v2 and 0xFF shl 16) or (v3 and 0xFF shl 8) or (v4 and 0xFF))
    }

    /**
     * This method will write the state of a boolean to the array at the pointer position
     */
    fun readBoolean(src: ByteArray, pointer: Int): Boolean {
        return read(src, pointer) == 1.toByte()
    }

    /**
     * This method will read a given byte from the array at the pointer position
     * Yes this method is useless in general but the whole base of this whole Class
     */
    fun read(src: ByteArray, pointer: Int): Byte {
        return src[pointer]
    }

}