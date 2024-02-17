package ru.ardyc.generator

import com.github.nwillc.ksvg.elements.SVG
import ru.ardyc.model.Repository

interface RepositoryBadgeGenerator {

    fun generate(repository: Repository): SVG

}