package ru.ardyc.controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.ardyc.render.render
import ru.ardyc.service.BadgeGenerationService
import java.net.URL

fun Route.configureBadgeEndpoints() {
    val badgeService: BadgeGenerationService by inject<BadgeGenerationService>()

    get("/") {
        call.respondTextWriter(contentType = ContentType.parse("image/svg+xml")) {
            append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n")
            badgeService
                .generate(URL("https://localhost"))
                .render(this)
        }
    }
}