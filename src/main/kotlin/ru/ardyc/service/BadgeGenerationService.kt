package ru.ardyc.service

import com.github.nwillc.ksvg.elements.SVG
import ru.ardyc.model.dto.GenerationStrategy

interface BadgeGenerationService {

    fun generate(repositoryName: String, generationStrategy: GenerationStrategy): SVG

}