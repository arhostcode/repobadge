package ru.ardyc.generator

import com.github.nwillc.ksvg.elements.Container
import com.github.nwillc.ksvg.elements.SVG
import ru.ardyc.model.Repository
import ru.ardyc.settings.Settings
import ru.ardyc.utils.splitIntoLines
import ru.ardyc.utils.substringMaxWithDots

class DefaultRepositoryBadgeGenerator(private val settings: Settings) : RepositoryBadgeGenerator {
    override fun generate(repository: Repository): SVG {
        val svg = SVG.svg(true) {
            height = "200"
            width = "800"
            viewBox = "0 0 800 200"

            attributes["xmlns"] = "http://www.w3.org/2000/svg"
            attributes["xmlns:xlink"] = "http://www.w3.org/1999/xlink"
            createBackground()
            createGithubLogo()
            text(82, 68, 24, repository.name.substringMaxWithDots(40))

            var description = repository.description.splitIntoLines(45)
            if (description.size > 4) {
                description = description.subList(0, 4).apply {
                    add("...")
                }
            }

            lines(82, 100, 16, description)
            text(450, 43, 20, "Statistics")

            createAttribute(500, 70, "Stars earned:", "star", repository.starsCount.toString())
            createAttribute(500, 92, "Issues/PRs opened:", "cloud", repository.issuesCount.toString())
            createAttribute(500, 114, "Language:", "letter", repository.language)
            createAttribute(500, 138, "Watchers:", "watchers", repository.watchersCount.toString())
            createAttribute(500, 160, "Forks:", "bug", repository.forksCount.toString())
        }
        return svg
    }

    private fun Container.createBackground() {
        rect {
            x = "3.5"
            y = "3.5"
            width = "793"
            height = "193"
            attributes["rx"] = "41.5"
            fill = "white"
            stroke = "black"
            strokeWidth = "7"
        }
    }

    private fun Container.createGithubLogo() {
        path {
            fill = "black"

            // Not using there d property, because it is very big to verification
            attributes["d"] = settings["github-logo"]
            attributes["fill-rule"] = "evenodd"
            attributes["clip-rule"] = "evenodd"
        }
    }

    private fun Container.createAttribute(x: Int, y: Int, description: String, logoId: String, value: String) {
        text(x, y, 15, description)
        text(x + 200, y, 15, value)
        createMiniLogo(logoId)
    }

    private fun Container.createMiniLogo(id: String) {
        path {
            stroke = "black"
            attributes["d"] = settings[id]
            attributes["stroke-linecap"] = "round"
            attributes["stroke-linejoin"] = "round"
            strokeWidth = "3"
        }
    }

    private fun Container.text(x: Int, y: Int, fontSize: Int, text: String) {
        text {
            this.x = x.toString()
            this.y = y.toString()
            body = text
            fill = "black"
            fontFamily = "arial"
            this.fontSize = fontSize.toString()
            attributes["font-weight"] = "bold"
        }
    }

    private fun Container.lines(x: Int, y: Int, fontSize: Int, text: List<String>) {
        text.forEachIndexed { num, it ->
            text {
                this.x = x.toString()
                this.y = (y + num * (fontSize + 2)).toString()
                body = it
                fill = "#565656"
                fontFamily = "arial"
                this.fontSize = fontSize.toString()
                attributes["font-weight"] = "bold"
            }
        }

    }

}

