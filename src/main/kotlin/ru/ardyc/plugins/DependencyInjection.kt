package ru.ardyc.plugins

import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import ru.ardyc.generator.DefaultRepositoryBadgeGenerator
import ru.ardyc.service.BadgeGenerationService
import ru.ardyc.service.DefaultBadgeGenerationService
import ru.ardyc.settings.ResourceSettingsReader
import ru.ardyc.settings.Settings

val prodModule = module {
    val settings = Settings(ResourceSettingsReader())
    val repositoryBadgeGenerator = DefaultRepositoryBadgeGenerator(settings)
    single<BadgeGenerationService> { DefaultBadgeGenerationService(repositoryBadgeGenerator) }
}

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(prodModule)
    }
}