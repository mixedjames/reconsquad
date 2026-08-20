plugins {
  id("application")
}

application {
  mainClass.set("io.github.mixedjames.rs.demos.Main")
  applicationDefaultJvmArgs = listOf("-Dsun.java2d.opengl=true")
}

dependencies {
  implementation(project(":packages:geometry"))
  testImplementation(libs.junit.jupiter)
}