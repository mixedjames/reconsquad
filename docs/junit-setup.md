# JUnit setup


## (1) build.gradle.kts
```
subprojects {
  tasks.withType<Test> {
    useJUnitPlatform()
  }
}
```

## (2) gradle/libs.versions.toml
```
[versions]
junit = "5.11.0"

[libraries]
junit-jupiter = { module = "org.junit.jupiter:junit-jupiter", version.ref = "junit" }
junit-platform-launcher = { module = "org.junit.platform:junit-platform-launcher" }
```

## (3) Per-package build.gradle.kts
```
dependencies {
  testImplementation(libs.junit.jupiter)
  testRuntimeOnly(libs.junit.platform.launcher)
}
```

Older documentation may not show the `testRuntimeOnly` line but Gradle 9 requires it.

## (4) Write a test
Tests live in the `./packages/package-name/src` folder but in the `test` subdirectory rather than
`main`. By convention the package structure is mirrored.

A minimal example:
```
package io.github.mixedjames.rs.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestTest {

  @Test
  public void test1() {
    assertEquals(2, 1 + 1);
  }
}
```


 ## (5) Run the tests
 ```
 ./gradlew test - runs tests alone
 ./gradlew build - runs tests as part of build process
 ```