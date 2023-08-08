plugins {
  kotlin("multiplatform")
  id("com.android.library")
}

group = "com.tidal.network-time"

kotlin {
  targetHierarchy.default()

  jvm()
  androidTarget {
    compilations.all {
      kotlinOptions {
        jvmTarget = "1.8"
      }
    }
  }
  listOf(
    macosX64(),
    macosArm64(),
    iosX64(),
    tvosSimulatorArm64(),
    tvosX64(),
    tvosArm64(),
    iosArm64(),
  ).forEach {
    it.binaries.framework {
      baseName = project.name
    }
  }
  js(IR) {
    browser()
    nodejs()
  }

  sourceSets {
    val versionKtor = "2.3.3"
    commonMain.get().dependencies {
      api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
      implementation("io.ktor:ktor-client-core:$versionKtor")
    }
    val jvmMain by getting {
      dependencies {
        implementation("io.ktor:ktor-client-okhttp:$versionKtor")
      }
    }
    val androidMain by getting {
      dependsOn(jvmMain)
    }
    val appleMain by getting {
      dependencies {
        implementation("io.ktor:ktor-client-darwin:$versionKtor")
      }
    }
    val jsMain by getting {
      dependencies {
        implementation("io.ktor:ktor-client-js:$versionKtor")
      }
    }
  }
}

android {
  compileSdk = 33
  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  defaultConfig {
    minSdk = 1
  }
  namespace = "com.tidal.networktime"
}
