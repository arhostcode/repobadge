package ru.ardyc.client

import ru.ardyc.model.Repository

interface GithubClient {
    fun resolveRepository(fullRepositoryName: String): Repository?
}