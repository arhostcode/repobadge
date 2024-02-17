package ru.ardyc.service

import java.net.URL

class DefaultBadgeGenerationService: BadgeGenerationService {
    override fun generate(url: URL) {
        println("Hello")
    }
}