import Dependencies.ConfigData.COMPILE_SDK_VERSION
import Dependencies.ConfigData.MIN_SDK_VERSION
import Dependencies.ConfigData.TARGET_SDK_VERSION

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.rootstrap.di"
    compileSdk = COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = MIN_SDK_VERSION
        targetSdk = TARGET_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))
    with(Dependencies.Kotlin) {
        implementation(SERIALIZATION)
    }
    with(Dependencies.Network) {
        implementation(OKHTTP)
        implementation(RETROFIT)
    }
    with(Dependencies.Koin) {
        implementation(CORE)
        implementation(ANDROID)
    }
}
