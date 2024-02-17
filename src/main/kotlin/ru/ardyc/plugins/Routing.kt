package ru.ardyc.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.ardyc.controller.configureBadgeEndpoints

fun Application.configureRouting() {
    routing {
        configureBadgeEndpoints()
    }
}
