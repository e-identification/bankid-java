plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.github.spotbugs.snom:spotbugs-gradle-plugin:6.0.0-rc.2")
    implementation("net.ltgt.gradle:gradle-errorprone-plugin:3.1.0")
    implementation("net.ltgt.gradle:gradle-nullaway-plugin:2.0.0")
}