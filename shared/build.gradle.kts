import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform) // Kotlin Multiplatform plugin
    alias(libs.plugins.androidLibrary) // Android Library plugin
    alias(libs.plugins.kotlinxSerialization) // Serialization plugin
    alias(libs.plugins.sqldelight) // SqlDelight plugin
    id("org.jetbrains.kotlin.kapt") // Kotlin KAPT plugin
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(kotlin("stdlib-common"))
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.runtime)
            implementation(libs.kotlinx.datetime)
            implementation(libs.koin.core)
            implementation("com.google.dagger:hilt-compiler:2.51.1") { // Manually specifying the version and adding exclusion
                exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-android")
            }
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.android.driver)
            implementation(libs.hilt.android)
            implementation(libs.hilt.navigation.compose)
            implementation(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "com.nithish.notes"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    databases {
        create("NoteDatabase") {
            packageName.set("com.nithish.notes.database")
        }
    }
}