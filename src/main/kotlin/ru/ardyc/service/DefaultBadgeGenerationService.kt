package ru.ardyc.service

import com.github.nwillc.ksvg.elements.SVG
import ru.ardyc.generator.RepositoryBadgeGenerator
import ru.ardyc.model.Repository
import java.net.URL

class DefaultBadgeGenerationService(private val repositoryBadgeGenerator: RepositoryBadgeGenerator) : BadgeGenerationService {
    override fun generate(url: URL): SVG {
        return repositoryBadgeGenerator.generate(
            Repository(
                "arhostcode/linktracker",
                "\uD83D\uDEE0\uFE0F Проект Tinkoff Java Course 2 семестр Framework for quickly creating connected applications in Kotlin with minimal effort",
                500, 50, 5, "Java", 5
            )
        )
    }

}