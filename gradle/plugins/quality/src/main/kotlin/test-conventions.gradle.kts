import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    id("java-library")
    id("java-gradle-plugin")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showExceptions = true
        showCauses = true
        showStackTraces = true
        exceptionFormat = TestExceptionFormat.FULL
    }
}