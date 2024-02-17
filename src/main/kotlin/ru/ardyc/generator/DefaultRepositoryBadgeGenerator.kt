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
            height = "100"
            width = "400"
            viewBox = "0 0 400 100"

            attributes["xmlns"] = "http://www.w3.org/2000/svg"
            attributes["xmlns:xlink"] = "http://www.w3.org/1999/xlink"
            createBackground()
            createGithubLogo()
            text(41, 34, 12, repository.name.substringMaxWithDots(25))

            var description = repository.description.splitIntoLines(40)
            if (description.size > 4) {
                description = description.subList(0, 4).apply {
                    add("...")
                }
            }

            lines(41, 50, 8, description)
            text(225, 21, 10, "Statistics")

            createAttribute(250, 35, "Stars earned:", "star", repository.starsCount.toString())
            createAttribute(250, 46, "Issues/PRs opened:", "cloud", repository.issuesCount.toString())
            createAttribute(250, 57, "Language:", "letter", repository.language)
            createAttribute(250, 69, "Watchers:", "watchers", repository.watchersCount.toString())
            createAttribute(250, 80, "Forks:", "bug", repository.forksCount.toString())
        }
        return svg
    }

    private fun Container.createBackground() {
        rect {
            x="1.75"
            y="1.75"
            width="396.5"
            height="96.5"
            attributes["rx"] = "20.75"
            fill = "white"
            stroke = "black"
            strokeWidth = "3.5"
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
        text(x, y, 7, description)
        text(x + 100, y, 7, value)
        createMiniLogo(logoId)
    }

    private fun Container.createMiniLogo(id: String) {
        path {
            stroke = "black"
            attributes["d"] = settings[id]
            attributes["stroke-linecap"] = "round"
            attributes["stroke-linejoin"] = "round"
            strokeWidth = "1.75"
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

