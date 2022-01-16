repositories {
  maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation("org.sourcegrade:jagr-grader-api:0.3")
    implementation("org.mockito:mockito-inline:4.2.0")
    implementation("org.powermock:powermock-api-mockito2:2.0.9")
    implementation("org.powermock:powermock-module-junit4:2.0.9")
    implementation("com.google.guava:guava:31.0.1-jre")
    implementation("fr.inria.gforge.spoon:spoon-core:10.0.0")
    implementation(project(":solution"))
}
