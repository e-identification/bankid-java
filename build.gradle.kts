plugins {
    id("java")
    id("checkstyle-conventions")
    id("spotbugs-conventions")
    id("errorprone-conventions")
    id("test-conventions")
    id("publish-conventions")
}

group = "dev.nicklasw"
version = "0.15.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jackson.core.databind)
    implementation(libs.jspecify)

    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.mockito.jupiter)
    testImplementation(libs.mockito.inline)

    testRuntimeOnly(libs.junit.jupiter.engine)
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    javadoc {
        options {
            // Suppress the warnings
            (this as CoreJavadocOptions).addStringOption("Xdoclint:none", "-quiet")
        }
    }
}

tasks.register("code-quality") {
    description = "Run Checkstyle, SpotBugs and ErrorProne analysis on both main and test classes"
    group = "code-quality"

    dependsOn(tasks.named("checkstyle").get())
    dependsOn(tasks.named("spotbugs").get())
    dependsOn(tasks.named("errorProne").get()) // Only on main classes
}

tasks.register("cq") {
    description = "Alias for code-quality"
    group = "code-quality"

    dependsOn(tasks.named("code-quality").get())
}