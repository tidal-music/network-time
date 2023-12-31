plugins {
  kotlin("multiplatform")
  id("org.jetbrains.compose")
}

kotlin {
  jvm()
  listOf(
    iosX64(),
    iosArm64(),
  ).forEach {
    it.binaries.framework {
      baseName = project.name
      isStatic = true
    }
  }

  sourceSets {
    commonMain.get().dependencies {
      dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")
        implementation(project(":library"))
      }
    }
  }
}
