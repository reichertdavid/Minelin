package de.rumeotech.minelin.networking

import java.net.Socket

class MinelinClient(private val socket: Socket) {

    init {
        val clientThread = Thread(
            {
                while (this.isConnected()) {

                }
            }, "Client #${socket.inetAddress.hostName}"
        )
    }

    /**
     * Checks if the client is connected by using some simple socket checks
     */
    fun isConnected(): Boolean {
        return this.socket.isConnected && !this.socket.isClosed && !this.socket.isInputShutdown && !this.socket.isOutputShutdown
    }
}