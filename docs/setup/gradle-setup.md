# Gradle setup

This file documents how I setup gradle for Recon Squad the first time. No one developing the
project should ever need to do this - `./gradlew` (Gradle wrapper) should handle everything but
I want to record what I did.

Clearly this is not how most people do it, nor is it how getting starting guides would instruct
one to do it. But I want to learn. Remember the project goals future James!

## (0) Goals
Create a Java gradle build setup that supports:
- Monorepo
- Subprojects in a `packages` directory

## (1) Prerequisites

- JDK installed
- Gradle installed

## (2) Getting Gradle wrapper setup to work
Create an empty `settings.gradle.kts` file.

If you don't, the gradle wrapper setup won't run.

## (3) Set up the Gradle wrapper
Run `gradle wrapper --gradle-version 9.7.1`

This will then create...
- The `./.gradle` private (local) settings directory
- The `./gradlew` and `./gradle.bat` scripts that allow it to bootstrap itself on new machines
- The `./gradle` directory for the gradle version we chose

### Don't do...
Tutorials generally suggest:
`gradle init --type java-application  --dsl kotlin`

I didn't want that because it does too much: it bootstraps the a whole app.

The I tried:
`gradle init --type basic --dsl kotlin`
([https://docs.gradle.org/current/userguide/build_init_plugin.html#sec:basic]).

This was better, but still did too much.

## (4) ./settings.gradle.kts
Minimal version looks like this:
```
rootProject.name = "recon-squad"
include("packages:demos", "packages:geometry")
```

## (5) ./gradle/libs.versions.toml
Not actually relevant to the minimal version but we create it now so that future sets can
assume it exists in a legal form:
```
[versions]

[libraries]

```

## (6) build.gradle.kts
```
plugins {
}

subprojects {
  apply(plugin = "java")

  repositories {
    /* Needed so that Gradle knows where to find dependencies */
    mavenCentral()
  }

  /* Future James: we originally did this using the syntax that shows up in simple tutorials:
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
}
```

## (7) Create the package directory structure & entry point
Each package exists as a directory within `./packages`

Each package directory then contains:
- `build.gradle.kts` (see below)
- `src` directory (all yours!)
- `build` directory (created and owned by Gradle)

The `src` directory is, in classic Java style, a hugely verbose mess of subdirectories that you
must name exactly right or woe betide you!

Best explained with an example:
```
For the package directory:
./packages/demos/src/

...create...
main/java/io/github/mixedjames/rs/demos
```

(VSCode folder name collapsing saves us here!)

## (8) Per-package build.gradle.kts
Library packages look like this:
```
dependencies {}
```

Executable packages look like this:
```
plugins {
  id("application")
}

application {
  mainClass.set("io.github.mixedjames.rs.demos.Main")
  applicationDefaultJvmArgs = listOf("-Dsun.java2d.opengl=true")
}

dependencies {
}
```

## (9) Build and run!
Global...
```
./gradlew build
./gradlew run
```

But, by the time you've got more than one executable entry point, you probably don't want to
do that.

Locally...
```
./gradlew :packages:demos:build
./gradlew :packages:demos:run
```
