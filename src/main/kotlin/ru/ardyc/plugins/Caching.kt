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
                    object : CacheControl(visibility = Visibility.Public) {
                        override fun toString(): String {
                            return "no-cache,no-store,public,max-age=0,must-revalidate"
                        }
                    }
                )

                else -> null
            }
        }
    }
}