package de.rumeotech.minelin.networking.packet.impl

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class PacketInfo(val id: Int, val state: PacketState)
