plugins {
    id("java-library")
    id("java-gradle-plugin")
    id("com.github.spotbugs")
}

spotbugs {
    toolVersion = "4.8.3"

    tasks.spotbugsMain {
        reports.create("html") {
            enabled = true
            setStylesheet("fancy-hist.xsl")
        }
    }
}

tasks.register("spotbugs") {
    description = "Run SpotBugs analysis on both main and test classes"
    group = "code-quality"

    dependsOn(tasks.named("spotbugsMain").get())
    dependsOn(tasks.named("spotbugsTest").get())
}