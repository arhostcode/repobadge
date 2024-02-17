package ru.ardyc.service

import com.github.nwillc.ksvg.elements.SVG
import com.github.nwillc.ksvg.elements.SVG.Companion.svg
import ru.ardyc.client.DefaultGithubClient
import ru.ardyc.client.GithubClient
import ru.ardyc.generator.RepositoryBadgeGenerator

class DefaultBadgeGenerationService(private val repositoryBadgeGenerator: RepositoryBadgeGenerator) :
    BadgeGenerationService {

    private val githubClient: GithubClient = DefaultGithubClient()

    override fun generate(repositoryName: String): SVG {
        val repository = githubClient.resolveRepository(repositoryName) ?: return svg {}
        return repositoryBadgeGenerator.generate(repository)
    }

}