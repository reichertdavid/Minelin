package de.rumeotech.minelin.configuration

import com.google.gson.Gson
import de.rumeotech.minelin.configuration.impl.ServerConfiguration
import java.io.File
import java.util.*

object ConfigurationManager {

    var serverConfiguration = ServerConfiguration(100, "Welcome to Minelin", 25565)
    val gson = Gson()

    fun saveConfig() {
        val propertiesFile = File("config.json")
        propertiesFile.writeText(gson.toJson(propertiesFile))
    }

    fun loadConfig() {
        val propertiesFile = File("config.json")
        if(propertiesFile.exists()) {
            serverConfiguration = gson.fromJson(propertiesFile.readText(), ServerConfiguration::class.java)
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