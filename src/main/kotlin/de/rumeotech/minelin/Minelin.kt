package de.rumeotech.minelin

import de.rumeotech.minelin.networking.MinelinServer
import org.apache.logging.log4j.LogManager

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

    @JvmStatic
    fun main(args: Array<String>) {
        LOGGER.info("Starting up Minelin $VERSION...")

        server = MinelinServer()
        server.start()
    }

}