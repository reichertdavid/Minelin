package de.rumeotech.minelin.configuration

import de.rumeotech.minelin.configuration.impl.ServerConfiguration
import org.json.JSONObject
import java.io.File
import java.util.*

object ConfigurationManager {

    var serverConfiguration = ServerConfiguration(100, "Welcome to Minelin", 25565)

    fun saveConfig() {
        val propertiesFile = File("config.json")
        propertiesFile.writeText(JSONObject(serverConfiguration).toString(4))
    }

    fun loadConfig() {
        val propertiesFile = File("config.json")
        if(propertiesFile.exists()) {
            val properties = propertiesFile.readText()
            val jsonConfig = JSONObject(properties)

            serverConfiguration = ServerConfiguration(jsonConfig.getInt("maxPlayers"), jsonConfig.getString("motd"), jsonConfig.getInt("port"))
        }
    }

    /**
     * This function returns the favicon as a formatted string
     */
    fun readFavicon(): String {
        val favicon = File("favicon.png")
        var value = "data:image/png;base64,<data>"
        if(favicon.exists()) {
            val imageBytes = favicon.readBytes()
            val base64 = Base64.getEncoder().encode(imageBytes)
            value = value.replace("<data>", String(base64))
        }
        return value
    }
}