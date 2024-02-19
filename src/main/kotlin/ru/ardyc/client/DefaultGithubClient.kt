package ru.ardyc.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.jetty.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.eclipse.jetty.util.ssl.SslContextFactory
import ru.ardyc.model.Repository

class DefaultGithubClient : GithubClient {

    private val client = HttpClient(Jetty) {
        engine {
            sslContextFactory = SslContextFactory.Client()
            clientCacheSize = 12
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override fun resolveRepository(fullRepositoryName: String): Repository? {
        try {
            return runBlocking {
                val token = System.getenv("GITHUB_TOKEN")

                val response: GithubResponse = client
                    .get("https://api.github.com/repos/$fullRepositoryName") {
                        headers {
                            if (token != null) {
                                append(HttpHeaders.Authorization, "Bearer $token")
                            }
                        }
                    }
                    .body()
                val repository = Repository(
                    response.fullName,
                    response.description ?: "",
                    response.starCount,
                    response.issuesCount,
                    response.watchers,
                    response.language,
                    response.forks
                )
                return@runBlocking repository
            }
        } catch (_: Exception) {
            return null
        }
    }

    @Serializable
    private data class GithubResponse(
        @SerialName("full_name") val fullName: String,
        val description: String? = null,
        val language: String,
        @SerialName("stargazers_count") val starCount: Int,
        @SerialName("open_issues") val issuesCount: Int,
        @SerialName("watchers") val watchers: Int,
        @SerialName("forks") val forks: Int
    )
}