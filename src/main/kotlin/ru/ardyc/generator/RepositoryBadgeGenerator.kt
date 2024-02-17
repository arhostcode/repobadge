package ru.ardyc.generator

import com.github.nwillc.ksvg.elements.SVG
import ru.ardyc.model.Repository
import ru.ardyc.model.dto.GenerationStrategy

interface RepositoryBadgeGenerator {

    fun generate(repository: Repository, generationStrategy: GenerationStrategy): SVG

}