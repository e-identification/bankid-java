import java.net.URI

plugins {
    id("java-library")
    id("java-gradle-plugin")
    id("maven-publish")
    id("signing")
}

tasks.withType<Javadoc>().configureEach {
    setDestinationDir(layout.projectDirectory.file("docs/").asFile)
}

publishing {
    repositories {
        maven {
            name = "ossrh"
            url = URI.create("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = findProperty("ossrhUsername").toString()
                password = findProperty("ossrhPassword").toString()
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name = "BankID SDK"
                artifactId = "bankid-sdk"
                description = "A SDK to interact with the BankID API"
                url = "https://github.com/NicklasWallgren/bankid-java-sdk"
                scm {
                    connection = "scm:git:git://github.com/NicklasWallgren/bankid-java-sdk.git"
                    developerConnection = "scm:git:ssh://github.com/NicklasWallgren/bankid-java-sdk.git"
                    url = "https://github.com/NicklasWallgren/bankid-java-sdk"
                }
                licenses {
                    license {
                        name = "MIT Licence"
                        url = "https://github.com/NicklasWallgren/bankid-java-sdk/blob/master/LICENSE"
                    }
                }
                developers {
                    developer {
                        id = "nicklas"
                        name = "NicklasWallgren"
                        email = "nicklas.wallgren@gmail.com"
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}