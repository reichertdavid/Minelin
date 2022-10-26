package de.rumeotech.minelin.networking.util.datatype

data class VarInt(var value: Int, var size: Byte) {

    fun updateSize(): Int {
        for (i in 1..4) {
            if (value and (-1 shl i) * 7 == 0) {
                return i
            }
        }
        return 5
    }

}