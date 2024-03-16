import net.ltgt.gradle.errorprone.errorprone

plugins {
    id("java-library")
    id("java-gradle-plugin")
    id("net.ltgt.errorprone")
    id("net.ltgt.nullaway")
}

dependencies {
    errorprone("com.google.errorprone:error_prone_core:2.25.0")
    errorprone("com.uber.nullaway:nullaway:0.10.24")
}

// Disable error prone by default
tasks.withType<JavaCompile>().configureEach {
    options.errorprone.isEnabled = false
}

@Suppress("UnstableApiUsage")
configurations {
    val classpathsIterable = Iterable {
        iterator {
            yield(compileClasspath.get())
            yield(runtimeClasspath.get())
        }
    }
    errorprone.get().setExtendsFrom(classpathsIterable)
}

val errorproneConfig = configurations.errorprone.get()

tasks.register("errorProne", JavaCompile::class) {
    options.isFork = true
    options.isIncremental = true
    options.annotationProcessorPath = errorproneConfig
    options.compilerArgs.addAll(arrayOf("-Werror"))

    source = fileTree("src/main/java")
    classpath = errorproneConfig
    destinationDirectory.set(file("${layout.buildDirectory}/classes/errorprone"))
    outputs.upToDateWhen() { false }

    options.errorprone {
        isEnabled = true
        disableWarningsInGeneratedCode = true
        ignoreUnknownCheckNames = false
        allErrorsAsWarnings = false
        errorproneArgs = listOf("-Xep:MissingSummary:OFF", "-Xlint:deprecation")

        nullaway {
            error()
            annotatedPackages.add("dev.eidentification.bankid")
        }
    }
}


tasks.named("check").get().dependsOn("errorProne")