plugins {
    id("java")
    id("io.freefair.lombok") version "8.3"

    id("checkstyle-conventions")
    id("spotbugs-conventions")
    id("errorprone-conventions")
    id("test-conventions")
}

group = "dev.nicklasw"
version = "0.14.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    implementation(libs.jackson.core.databind)
    implementation(libs.findbugs.annotations)
    implementation(libs.mccue.jsr305)
    implementation(libs.jspecify)
    implementation(libs.findbugs.annotations)

    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.mockito.jupiter)
    testImplementation(libs.mockito.inline)

    testRuntimeOnly(libs.junit.jupiter.engine)
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
