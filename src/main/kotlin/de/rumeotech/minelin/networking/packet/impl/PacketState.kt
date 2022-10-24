package de.rumeotech.minelin.networking.packet.impl

enum class PacketState(val id: Int) {

    HANDSHAKING(0),
    STATUS(1),
    LOGIN(2),
    PLAY(3);

}