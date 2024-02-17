package ru.ardyc.controller

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.configureBadgeEndpoints() {
    get("/") {
        call.respondText("Hello World!")
    }
}