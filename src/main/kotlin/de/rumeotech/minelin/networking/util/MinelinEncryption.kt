package de.rumeotech.minelin.networking.util

import java.security.KeyPair
import java.security.KeyPairGenerator

object MinelinEncryption {

    fun generateKeyPair() : KeyPair {
        val generator = KeyPairGenerator.getInstance("RSA")
        generator.initialize(1024)
        return generator.generateKeyPair()
    }

}