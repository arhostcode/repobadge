package ru.ardyc.service

import com.github.nwillc.ksvg.elements.SVG
import ru.ardyc.client.DefaultGithubClient
import ru.ardyc.client.GithubClient
import ru.ardyc.generator.RepositoryBadgeGenerator
import ru.ardyc.model.Repository
import ru.ardyc.model.dto.GenerationStrategy

class DefaultBadgeGenerationService(private val repositoryBadgeGenerator: RepositoryBadgeGenerator) :
    BadgeGenerationService {

    private val githubClient: GithubClient = DefaultGithubClient()

    override fun generate(repositoryName: String, generationStrategy: GenerationStrategy): SVG {
//        val repository = githubClient.resolveRepository(repositoryName) ?: return svg {}
        val repository = Repository(
            name = "arhostcode/linktracker",
            description = "fun text(block: TEXT.() -> Unit): TEXT = add(TEXT(validation), block)",
            5, 5, 5, "Java", 5
        )
        return repositoryBadgeGenerator.generate(repository, generationStrategy)
    }

}