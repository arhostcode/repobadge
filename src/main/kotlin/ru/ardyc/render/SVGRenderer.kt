package ru.ardyc.render

import com.github.nwillc.ksvg.RenderMode
import com.github.nwillc.ksvg.elements.Element
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.jvm.isAccessible

fun Element.render(appendable: Appendable) {
    val field = Element::class.declaredMembers.find { it.name == "name" }
    field!!.isAccessible = true
    val name = field.call(this) as String
    appendable.append("<$name")
    getAttributes(RenderMode.FILE)
        .entries
        .sortedBy { it.key }
        .forEach { entry ->
            appendable.append(' ')
            appendable.append(entry.key)
            appendable.append("=\"")
            appendable.append(entry.value)
            appendable.append('"')
        }
    if (!hasContent()) {
        appendable.append("/>\n")
    } else {
        appendable.append('>')
        if (hasBody()) {
            appendable.escapeHTML(body)
        } else {
            appendable.append('\n')
        }
        children.forEach {
            it.render(appendable)
        }
        appendable.append("</$name>\n")
    }
}

fun Appendable.escapeHTML(csq: CharSequence) {
    for (c in csq) {
        when {
            c == '"' || c == '<' || c == '>' || c == '&' -> {
                append("&#")
                append(c.code.toString())
                append(';')
            }
            else -> append(c)
        }
    }
}