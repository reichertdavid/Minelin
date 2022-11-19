package de.rumeotech.minelin.networking.util.models.chat

data class ChatObject(
    val text: String,
    val bold: Boolean = false,
    val italic: Boolean = false,
    val underlined: Boolean = false,
    val strikethrough: Boolean = false,
    val obfuscated: Boolean = false,
    val color: String = "reset",
    val extra: Array<ChatObject> = arrayOf(ChatObject(""))
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChatObject

        if (text != other.text) return false
        if (bold != other.bold) return false
        if (italic != other.italic) return false
        if (underlined != other.underlined) return false
        if (strikethrough != other.strikethrough) return false
        if (obfuscated != other.obfuscated) return false
        if (color != other.color) return false
        if (!extra.contentEquals(other.extra)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = text.hashCode()
        result = 31 * result + bold.hashCode()
        result = 31 * result + italic.hashCode()
        result = 31 * result + underlined.hashCode()
        result = 31 * result + strikethrough.hashCode()
        result = 31 * result + obfuscated.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + extra.contentHashCode()
        return result
    }
}