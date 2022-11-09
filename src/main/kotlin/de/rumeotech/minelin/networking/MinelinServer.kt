package de.rumeotech.minelin.networking

import de.rumeotech.minelin.configuration.ConfigurationManager
import de.rumeotech.minelin.networking.packet.PacketHandler
import org.apache.logging.log4j.LogManager
import java.io.IOException
import java.net.ServerSocket
import java.util.logging.Logger

class MinelinServer {

    // Logger instance
    private val LOGGER = LogManager.getLogger()

    /**
     * This boolean will define if the server is runnning
     */
    var isRunning = false
        private set

    /**
     * ServerSocket object to receive TCP/IP Connection from the Minecraft client
     */
    private val serverSocket: ServerSocket = ServerSocket(ConfigurationManager.serverConfiguration.port)

    /**
     * This is a list of all current connected Clients
     */
    val connectedClients = mutableListOf<MinelinClient>()

    val packetHandler = PacketHandler()

    fun start() {
        // Set the running state of the server to true
        this.isRunning = true

        // Create a separate thread for new connections
        val listener = Thread({
            while (this.isRunning) {
                try {
                    val socket = this.serverSocket.accept()
                    this.connectedClients.add(MinelinClient(socket))
D                } catch (e: IOException) {
                    LOGGER.error("Failed connection - info: ", e)
                }
            }
        }, "Connection Listener")
        listener.start()

        LOGGER.info("Successfully started up all services")
    }
}