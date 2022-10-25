package de.rumeotech.minelin.configuration

import de.rumeotech.minelin.configuration.impl.ServerConfiguration
import org.json.JSONObject
import java.io.File

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

}