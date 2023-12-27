plugins {
    id("java-library")
    id("java-gradle-plugin")
    id("checkstyle")
}

checkstyle {
    toolVersion = "10.3.2"
    isIgnoreFailures = false
    maxWarnings = 0
}

tasks.register("checkstyle") {
    description = "Run Checkstyle analysis on both main and test classes"
    group = "code-quality"

    dependsOn(tasks.named("checkstyleMain").get())
    dependsOn(tasks.named("checkstyleTest").get())
}