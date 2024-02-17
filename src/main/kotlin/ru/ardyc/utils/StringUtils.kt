package ru.ardyc.utils

fun String.splitIntoLines(maxLength: Int): MutableList<String> {
    val words = this.split("\\s+".toRegex())
    val result = mutableListOf<String>()
    var currentLine = StringBuilder()

    for (word in words) {
        if (currentLine.length + word.length + 1 <= maxLength) {
            if (currentLine.isNotEmpty()) {
                currentLine.append(" ")
            }
            currentLine.append(word)
        } else {
            result.add(currentLine.toString())
            currentLine = StringBuilder(word)
        }
    }

    if (currentLine.isNotEmpty()) {
        result.add(currentLine.toString())
    }

    return result
}

fun String.substringMaxWithDots(maxLength: Int): String {
    if (this.length <= maxLength) return this
    return this.substring(0..maxLength) + "..."
}