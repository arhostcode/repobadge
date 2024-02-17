package ru.ardyc.settings

data class Settings(
    private val settingsReader: SettingsReader,
    private val values: MutableMap<String, String> = mutableMapOf()
) {

    operator fun get(id: String): String {
        return values.computeIfAbsent(id, settingsReader::read)
    }

}

interface SettingsReader {
    fun read(id: String): String
}