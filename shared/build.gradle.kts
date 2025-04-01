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
                    jvmTarget.set(JvmTarget.JVM_17)
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
//            implementation(libs.koin.core)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.android.driver)
            implementation(libs.kotlinx.datetime)
        }
    }
}

android {
    namespace = "com.nithish.notes"
    compileSdk = 35
    defaultConfig {
        minSdk = 26
    }
    buildFeatures {
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        implementation("com.android.tools:desugar_jdk_libs:2.1.5")
    }
}

sqldelight {
    databases {
        create("NoteDatabase") {
            packageName.set("com.nithish.notes.database")
        }
    }
}