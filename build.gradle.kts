plugins {
  id("com.diffplug.spotless") version "6.25.0" apply false
}

subprojects {
  apply(plugin = "java")
  apply(plugin = "com.diffplug.spotless")

  repositories {
    /* Needed so that Gradle knows where to find dependencies */
    mavenCentral()
  }

  /* Note for Future James:
     We originally did this using the syntax that shows up in simple tutorials:
     java { toolchain { languageVersion } }

     This doesn't work for reasons related to how Gradle's Kotlin DSL works. I can't find an 
     excellent docs source for this but try:
     https://discuss.gradle.org/t/gradle-kotlin-problems-with-subprojects-and-plugins/34851

     Anyway, the correct way to do this is to use the configure<JavaPluginExtension> syntax, which
     is what we have below.
  */
  configure<JavaPluginExtension> {
    toolchain { languageVersion.set(JavaLanguageVersion.of(26)) }
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    /* Note for Future James:
       We originally did this using the syntax that shows up in simple tutorials:
       java { googleJavaFormat() }

       This generates a binary incompatibility so we need to bump the version.
       I can't find a great reference for this but try:
       https://www.mail-archive.com/dev@iotdb.apache.org/msg09278.html
     */
    java { googleJavaFormat("1.28.0") }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}