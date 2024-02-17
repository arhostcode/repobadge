package ru.ardyc.service

import com.github.nwillc.ksvg.elements.SVG

interface BadgeGenerationService {

    fun generate(repositoryName: String): SVG

}