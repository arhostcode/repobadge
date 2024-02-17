package ru.ardyc.service

import com.github.nwillc.ksvg.elements.SVG
import java.net.URL

interface BadgeGenerationService {

    fun generate(url: URL): SVG

}