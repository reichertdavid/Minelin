package de.rumeotech.minelin.networking.util


object DatatypeWriter {

    fun writeVarInt(dest: ByteArray, pointer: Int, varInt: Int): Int {
        var value = varInt
        var x = pointer
        do {
            var temp = (value and 127).toByte()
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value = value ushr 7
            if (value != 0) {
                temp = (temp.toInt() or 128).toByte()
            }
            dest[x++] = temp
        } while (value != 0)
        return x
    }

    fun writeVarLong(dest: ByteArray, pointer: Int, varLong: Long): Int {
        var value = varLong
        var x = pointer
        do {
            var temp = (value and 127).toByte()
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value = value ushr 7
            if (value != 0L) {
                temp = (temp.toInt() or 128).toByte()
            }
            dest[x++] = temp
        } while (value != 0L)
        return x
    }

    /**
     * This method will write the value of a float to the array at the pointer position
     */
    fun write(dest: ByteArray, pointer: Int, value: Float): Int {
        return write(dest, pointer, value.toBits())
    }

    /**
     * This method will write the value of a double to the array at the pointer position
     */
    fun write(dest: ByteArray, pointer: Int, value: Double): Int {
        return write(dest, pointer, value.toBits())
    }

    /**
     * This method will write the value of a short to the array at the pointer position
     */
    fun write(dest: ByteArray, pointer: Int, value: Short): Int {
        var p = pointer
        var v = value.toInt()
        dest[p++] = (v shr 8 and 0xFF).toByte()
        dest[p++] = (v shr 0 and 0xFF).toByte()
        return p
    }


    /**
     * This method will write the value of a string to the array at the pointer position
     */
    fun write(dest: ByteArray, pointer: Int, value: String): Int {
        var x = pointer
        x = write(dest, x, value.length.toShort())
        value.toByteArray().forEach { x = write(dest, pointer, it) }
        return x
    }

    /**
     * This method will write the value of a long to the array at the pointer position
     */
    fun write(dest: ByteArray, pointer: Int, value: Long): Int {
        var p = pointer
        dest[p++] = (value shr 56 and 0xFF).toByte()
        dest[p++] = (value shr 48 and 0xFF).toByte()
        dest[p++] = (value shr 40 and 0xFF).toByte()
        dest[p++] = (value shr 32 and 0xFF).toByte()
        dest[p++] = (value shr 24 and 0xFF).toByte()
        dest[p++] = (value shr 16 and 0xFF).toByte()
        dest[p++] = (value shr 8 and 0xFF).toByte()
        dest[p++] = (value shr 0 and 0xFF).toByte()
        return p
    }

    /**
     * This method will write the value of a string to the array at the pointer position
     */
    fun write(dest: ByteArray, pointer: Int, value: Int): Int {
        var p = pointer
        dest[p++] = (value shr 24 and 0xFF).toByte()
        dest[p++] = (value shr 16 and 0xFF).toByte()
        dest[p++] = (value shr 8 and 0xFF).toByte()
        dest[p++] = (value shr 0 and 0xFF).toByte()
        return p
    }

    /**
     * This method will write the state of a boolean to the array at the pointer position
     */
    fun write(dest: ByteArray, pointer: Int, value: Boolean): Int {
        return write(dest, pointer, if (value) 1 else 0)
    }

    /**
     * This method will write a given byte to the array at the pointer position
     * Yes this method is useless in general but the whole base of this whole Class
     */
    fun write(dest: ByteArray, pointer: Int, value: Byte): Int {
        var p = pointer
        dest[p++] = value
        return p
    }

}