package ru.ardyc.settings

class ResourceSettingsReader : SettingsReader {
    override fun read(id: String): String {
        return getResourceAsText("/$id")
    }

    private fun getResourceAsText(path: String): String =
        object {}.javaClass.getResource(path)?.readText() ?: ""
}