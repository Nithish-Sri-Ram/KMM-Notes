plugins {
    alias(libs.plugins.androidApplication) // Android Application plugin
    alias(libs.plugins.kotlinAndroid) // Kotlin Android plugin
    alias(libs.plugins.compose.compiler) // Compose Compiler plugin
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android") version "2.51.1"
}

android {
    namespace = "com.nithish.notes.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.nithish.notes.android"
        minSdk = 26
        //noinspection EditedTargetSdkVersion
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    kapt {
        correctErrorTypes = true
        includeCompileClasspath = true
    }
    hilt{
        enableAggregatingTask = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    dependencies {
        implementation(project(":shared"))
        implementation(libs.compose.ui)
        implementation(libs.compose.material3)
        implementation(libs.androidx.activity.compose)
        implementation(libs.kotlinx.datetime)
        debugImplementation(libs.compose.ui.tooling)

        // Hilt dependencies
        implementation(libs.hilt.android)
        implementation(libs.hilt.navigation.compose)
        kapt(libs.hilt.android.compiler)
        implementation("com.squareup:javapoet:1.12.0")
        kapt("com.squareup:javapoet:1.12.0")
    }
}
