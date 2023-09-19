buildscript {
  repositories {
    gradlePluginPortal()
    google()
  }
  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
    classpath("com.android.tools.build:gradle:8.0.2")
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()
  }
}
