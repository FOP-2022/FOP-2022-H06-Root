plugins {
    java
    id("org.sourcegrade.style") version "1.2.0"
}
allprojects {
    apply(plugin = "java")
    apply(plugin = "org.sourcegrade.style")
    repositories {
        mavenCentral()
    }
    java {
        withSourcesJar()
    }
    tasks {
        jar {
            archiveFileName.set("${rootProject.name}-${project.name}.jar")
        }
        named<Jar>("sourcesJar") {
            archiveFileName.set("${rootProject.name}-${project.name}-sources.jar")
        }
    }
}
