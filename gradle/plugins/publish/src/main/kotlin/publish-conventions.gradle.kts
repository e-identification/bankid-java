plugins {
    id("java-library")
    id("java-gradle-plugin")
    id("com.vanniktech.maven.publish")
}

afterEvaluate {
  tasks.named("generateMetadataFileForPluginMavenPublication") {
    dependsOn("plainJavadocJar")
  }
}

mavenPublishing {
  coordinates("dev.eidentification", "bankid-sdk", version = version.toString())

  signAllPublications()

  pom {
    name.set("BankID SDK")
    description.set("A SDK to interact with the BankID API.")
    url.set("https://github.com/NicklasWallgren/bankid-java")
    licenses {
      license {
        name.set("MIT Licence")
        url.set("https://github.com/NicklasWallgren/bankid-java/blob/master/LICENSE")
        distribution.set("https://github.com/NicklasWallgren/bankid-java/blob/master/LICENSE")
      }
    }
    developers {
      developer {
        id.set("nicklas")
        name.set("NicklasWallgren")
        url.set("nicklas.wallgren@gmail.com")
      }
    }
    scm {
      url.set("https://github.com/NicklasWallgren/bankid-java")
      connection.set("scm:git:git://github.com/NicklasWallgren/bankid-java.git")
      developerConnection.set("scm:git:ssh://github.com/NicklasWallgren/bankid-java.git")
    }
  }
}