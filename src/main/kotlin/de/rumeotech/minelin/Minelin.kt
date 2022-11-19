package de.rumeotech.minelin

import de.rumeotech.minelin.configuration.ConfigurationManager
import de.rumeotech.minelin.networking.MinelinServer
import de.rumeotech.minelin.networking.util.MinelinEncryption
import org.apache.logging.log4j.LogManager
import java.security.KeyPair

object Minelin {

    /**
     * This is our Log4j Logger instance
     */
    val LOGGER = LogManager.getLogger()

    /**
     * This is our version constant
     * TODO: change this to a gradle task and version.properties file
     */
    private const val VERSION = "1.0.0-ALPHA"

    /**
     * Instance of the MinelinServer class, this will be initialized with start of the application
     */
    lateinit var server: MinelinServer

    /**
     * RSA Keypair for Protocol Encryption
     */
    lateinit var keyPair: KeyPair

    @JvmStatic
    fun main(args: Array<String>) {
        LOGGER.info("Starting up Minelin $VERSION...")

        Runtime.getRuntime().addShutdownHook(Thread {
            ConfigurationManager.saveConfig()
        })

        LOGGER.info("Generating 1024-bit RSA Keypair...")
        keyPair = MinelinEncryption.generateKeyPair()

        server = MinelinServer()
        server.start()
        LOGGER.info("Successfully started minelin!")
    }

}