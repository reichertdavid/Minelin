package de.rumeotech.minelin.configuration.impl

data class ServerConfiguration(
    /* The maximum amount of players that can join the server  */
    val maxPlayers: Int = 12,
    /* The MOTD (motto of the day) display in the server list */
    val motd: String = "Welcome to Minelin!",
    /* Port of the server (default 25565) */
    val port: Int = 25565,
    /* prevent proxy connections from the client */
    val preventProxyConnections: Boolean = false
)
