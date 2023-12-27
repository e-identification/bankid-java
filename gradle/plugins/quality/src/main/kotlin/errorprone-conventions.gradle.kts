import net.ltgt.gradle.errorprone.errorprone

plugins {
    id("java-library")
    id("java-gradle-plugin")
    id("net.ltgt.errorprone")
}

dependencies {
    errorprone("com.google.errorprone:error_prone_core:2.18.0")
}

// Disable error prone by default
@Suppress("UnstableApiUsage")
tasks.withType<JavaCompile>().configureEach {
    options.errorprone.isEnabled = false
}

configurations {
    val iterable = Iterable {
        iterator {
            yield(compileClasspath.get())
            yield(runtimeClasspath.get())
        }
    }

    errorprone.get().setExtendsFrom(iterable)
}

@Suppress("UnstableApiUsage")
tasks.register("errorProne", JavaCompile::class) {
    options.isFork = true
    options.isIncremental = true
    options.annotationProcessorPath = configurations.errorprone.get()
    options.compilerArgs.addAll(arrayOf("-Werror"))

    source = fileTree("src/main/java")
    classpath = configurations.errorprone.get()
    destinationDirectory.set(file("$buildDir/classes/errorprone"))
    outputs.upToDateWhen() { false }

    options.errorprone {
        isEnabled = true
        disableWarningsInGeneratedCode = true
        ignoreUnknownCheckNames = false
        allErrorsAsWarnings = false
        errorproneArgs = listOf("-Xep:MissingSummary:OFF", "-Xlint:deprecation")
    }
}

tasks.named("check").get().dependsOn("errorProne")