package de.rumeotech.minelin.networking.util

enum class DataTypes(val size: Int) {

    BYTE(1), BOOL(1),
    SHORT(2), CHAR(2),
    INT(4), FLOAT(4),
    LONG(8), DOUBLE(8)

}