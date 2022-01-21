repositories {
    mavenLocal()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    compileOnly("org.sourcegrade:jagr-grader-api:0.3")
    implementation("org.mockito:mockito-inline:4.2.1-SNAPSHOT")
    implementation("org.powermock:powermock-api-mockito2:2.0.9")
    implementation("org.powermock:powermock-module-junit4:2.0.9")
    implementation("com.google.guava:guava:31.0.1-jre")
    implementation("fr.inria.gforge.spoon:spoon-core:10.0.0")
    implementation(project(":solution"))
}
tasks {
    create<Jar>("buildLibs") {
        group = "build"
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        val runtimeDeps = sourceSets.main.get().runtimeClasspath.mapNotNull {
            if (it.path.toLowerCase().contains("h06")) {
                null
            } else if (it.isDirectory) {
                it
            } else {
                zipTree(it)
            }
        }
        from(runtimeDeps)
        archiveFileName.set("h06-libs.jar")
    }
}
