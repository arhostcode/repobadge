package ru.ardyc.generator

import com.github.nwillc.ksvg.elements.Container
import com.github.nwillc.ksvg.elements.SVG
import ru.ardyc.model.Repository
import ru.ardyc.model.dto.GenerationStrategy
import ru.ardyc.settings.Settings
import ru.ardyc.utils.splitIntoLines
import ru.ardyc.utils.substringMaxWithDots

class DefaultRepositoryBadgeGenerator(private val settings: Settings) : RepositoryBadgeGenerator {
    override fun generate(repository: Repository, generationStrategy: GenerationStrategy): SVG {
        val svg = SVG.svg(true) {

            style {
                body = """
    .fillOpacity {
      fill: transparent;
      animation: fadeIn 3s forwards;
    }
    
    .strokeOpacity {
      stroke: transparent;
      animation: fadeInStroke 3s forwards;
    }

    @keyframes fadeIn {
      from { fill: transparent; }
      to { fill: ${generationStrategy.textColor}; }
    }
    
    @keyframes fadeInStroke {
      from { stroke: transparent; }
      to { stroke: ${generationStrategy.textColor}; }
    }
                """.trimIndent()
            }

            height = "100"
            width = "400"
            viewBox = "0 0 400 100"

            attributes["xmlns"] = "http://www.w3.org/2000/svg"
            attributes["xmlns:xlink"] = "http://www.w3.org/1999/xlink"
            createBackground(generationStrategy)
            createGithubLogo(generationStrategy)
            text(41, 34, 12, repository.name.substringMaxWithDots(25), generationStrategy)

            var description = repository.description.splitIntoLines(40)
            if (description.size > 4) {
                description = description.subList(0, 4).apply {
                    add("...")
                }
            }

            lines(41, 50, 8, description, generationStrategy)
            text(235, 21, 10, "Statistics", generationStrategy)

            createAttribute(250, 35, "Stars earned:", "star", repository.starsCount.toString(), generationStrategy)
            createAttribute(
                250,
                46,
                "Issues/PRs opened:",
                "cloud",
                repository.issuesCount.toString(),
                generationStrategy
            )
            createAttribute(250, 57, "Language:", "letter", repository.language, generationStrategy)
            createAttribute(250, 69, "Watchers:", "watchers", repository.watchersCount.toString(), generationStrategy)
            createAttribute(250, 80, "Forks:", "bug", repository.forksCount.toString(), generationStrategy)
        }
        return svg
    }

    private fun Container.createBackground(generationStrategy: GenerationStrategy) {
        rect {
            x = "1.75"
            y = "1.75"
            width = "396.5"
            height = "96.5"
            attributes["rx"] = "20.75"
            fill = generationStrategy.backgroundColor
            attributes["fill-opacity"] = generationStrategy.opacity.toString()
            stroke = "black"
            strokeWidth = if (generationStrategy.isStrokeEnabled) "3.5" else "0"
        }
    }

    private fun Container.createGithubLogo(generationStrategy: GenerationStrategy) {
        path {
            cssClass = "fillOpacity"
            fill = generationStrategy.textColor

            // Not using there d property, because it is very big to verification
            attributes["d"] = settings["github-logo"]
            attributes["fill-rule"] = "evenodd"
            attributes["clip-rule"] = "evenodd"
        }
    }

    private fun Container.createAttribute(
        x: Int,
        y: Int,
        description: String,
        logoId: String,
        value: String,
        generationStrategy: GenerationStrategy
    ) {
        text(x, y, 7, description, generationStrategy)
        text(x + 100, y, 7, value, generationStrategy)
        createMiniLogo(logoId, generationStrategy)
    }

    private fun Container.createMiniLogo(id: String, generationStrategy: GenerationStrategy) {
        path {
            cssClass = "strokeOpacity"
            fill = generationStrategy.backgroundColor
            stroke = generationStrategy.textColor
            attributes["d"] = settings[id]
            attributes["stroke-linecap"] = "round"
            attributes["stroke-linejoin"] = "round"
            attributes["fill-opacity"] = "0"
            strokeWidth = "1.75"
        }
    }

    private fun Container.text(x: Int, y: Int, fontSize: Int, text: String, generationStrategy: GenerationStrategy) {
        text {
            cssClass = "fillOpacity"
            this.x = x.toString()
            this.y = y.toString()
            body = text
            fill = generationStrategy.textColor
            fontFamily = "arial"
            this.fontSize = fontSize.toString()
            attributes["font-weight"] = "bold"
        }
    }

    private fun Container.lines(
        x: Int,
        y: Int,
        fontSize: Int,
        text: List<String>,
        generationStrategy: GenerationStrategy
    ) {
        text.forEachIndexed { num, it ->
            text {
                cssClass = "fillOpacity"
                this.x = x.toString()
                this.y = (y + num * (fontSize + 2)).toString()
                body = it
                fill = generationStrategy.textColor
                fontFamily = "arial"
                this.fontSize = fontSize.toString()
                attributes["font-weight"] = "bold"
            }
        }

    }

}

