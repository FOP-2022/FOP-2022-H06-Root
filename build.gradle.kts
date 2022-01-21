plugins {
    java
    id("org.sourcegrade.style") version "1.1.0"
}
allprojects {
    apply(plugin = "java")
    // apply(plugin = "org.sourcegrade.style")
    repositories {
        mavenLocal()
        mavenCentral()
    }
    java {
        withSourcesJar()
    }
    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
            sourceCompatibility = "11"
            targetCompatibility = "11"
        }
        jar {
            archiveFileName.set("${rootProject.name}-${project.name}.jar")
        }
        named<Jar>("sourcesJar") {
            archiveFileName.set("${rootProject.name}-${project.name}-sources.jar")
        }
    }
}

tasks {
    create<Jar>("graderJar") {
        group = "build"
        afterEvaluate {
            archiveFileName.set("FOP-2022-H06-${project.version}.jar")
            from(project(":grader").sourceSets.main.get().allSource)
            from(project(":solution").sourceSets.main.get().allSource)
        }
    }
}
