import org.sourcegrade.submitter.submit

plugins {
    java
    application
    id("org.sourcegrade.submitter") version "0.4.0"
}

version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

submit {
    assignmentId = "h06"
    studentId = "ab12cdef"
    firstName = "sol_first"
    lastName = "sol_last"
    requireTests = false
}

val grader: SourceSet by sourceSets.creating {
    val test = sourceSets.test.get()
    compileClasspath += test.output + test.compileClasspath
    runtimeClasspath += output + test.runtimeClasspath
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    "graderCompileOnly"("org.sourcegrade:jagr-launcher:0.4.0") {
        exclude("org.jetbrains", "annotations")
    }
    "graderImplementation"("org.ow2.asm:asm-util:9.2")
    "graderImplementation"("org.mockito:mockito-core:4.4.0")
    "graderImplementation"("com.google.guava:guava:31.1-jre")
    "graderImplementation"("fr.inria.gforge.spoon:spoon-core:10.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks {
    val runDir = File("build/run")
    named<JavaExec>("run") {
        doFirst {
            runDir.mkdirs()
        }
        workingDir = runDir
    }
    test {
        doFirst {
            runDir.mkdirs()
        }
        workingDir = runDir
        useJUnitPlatform()
    }
    val graderTest by creating(Test::class) {
        group = "verification"
        doFirst {
            runDir.mkdirs()
        }
        workingDir = runDir
        testClassesDirs = grader.output.classesDirs
        classpath = grader.compileClasspath + grader.runtimeClasspath
        useJUnitPlatform()
    }
    named("check") {
        dependsOn(graderTest)
    }
    val graderJar by creating(Jar::class) {
        group = "build"
        afterEvaluate {
            archiveFileName.set("FOP-2022-H06-${project.version}.jar")
            from(sourceSets.main.get().allSource)
            from(sourceSets.test.get().allSource)
            from(grader.allSource)
        }
    }
    val graderLibs by creating(Jar::class) {
        group = "build"
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        // don't include Jagr's runtime dependencies
        val jagrRuntime = configurations["graderCompileClasspath"]
            .resolvedConfiguration
            .firstLevelModuleDependencies
            .first { it.moduleGroup == "org.sourcegrade" && it.moduleName == "jagr-launcher" }
            .allModuleArtifacts
            .map { it.file }

        val runtimeDeps = grader.runtimeClasspath.mapNotNull {
            if (it.path.toLowerCase().contains("h06") || jagrRuntime.contains(it)) {
                null
            } else if (it.isDirectory) {
                it
            } else {
                zipTree(it)
            }
        }
        from(runtimeDeps)
        archiveFileName.set("FOP-2022-H06-${project.version}-libs.jar")
    }
    create("graderAll") {
        group = "build"
        dependsOn(graderJar, graderLibs)
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    jar {
        enabled = false
    }
}
