# Setting up Spotless

All changes are made to `./build.gradle.kts`

## (1) Add plugin

```
plugins {
  id("com.diffplug.spotless") version "6.25.0" apply false
}
```

## (2) Apply plugin
```
subprojects {
  apply(plugin = "com.diffplug.spotless")
}
```

## (3) Configure plugin
```
subprojects {
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
}
```

## (4) Run Spotless
```
./gradlew spotlessCheck
./gradlew spotlessApply
```

spotlessCheck runs as part of build step.