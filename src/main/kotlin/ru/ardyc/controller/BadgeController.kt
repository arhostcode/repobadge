package ru.ardyc.controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.ardyc.model.dto.GenerationStrategy
import ru.ardyc.render.render
import ru.ardyc.service.BadgeGenerationService

fun Route.configureBadgeEndpoints() {
    val badgeService: BadgeGenerationService by inject<BadgeGenerationService>()

    get("/") {
        call.respondTextWriter(contentType = ContentType.parse("image/svg+xml")) {
            val params = call.request.queryParameters
            append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n")
            badgeService
                .generate(
                    params["repository"] ?: "", GenerationStrategy(
                        backgroundColor = "#" + (params["background-color"] ?: "FFFFFF"),
                        opacity = params["opacity"]?.toDouble() ?: 0.1,
                        isStrokeEnabled = params["stroke"]?.toBoolean() ?: false,
                        textColor = "#" + (params["text-color"] ?: "000000"),
                    )
                )
                .render(this)
        }
    }
}