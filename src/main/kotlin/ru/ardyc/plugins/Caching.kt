package ru.ardyc.plugins

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cachingheaders.*

fun Application.configureCaching() {
    install(CachingHeaders) {
        options { _, content ->
            when (content.contentType?.withoutParameters()) {
                ContentType.Image.SVG -> CachingOptions(
                    CacheControl.NoStore(visibility = CacheControl.Visibility.Public)
                )
                else -> null
            }
        }
    }
}