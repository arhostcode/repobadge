package ru.ardyc.plugins

import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import ru.ardyc.service.BadgeGenerationService
import ru.ardyc.service.DefaultBadgeGenerationService

val prodModule = module {
    single<BadgeGenerationService> { DefaultBadgeGenerationService() }
}

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(prodModule)
    }
}