import Dependencies.Common.JAVA_TARGET
import Dependencies.Common.JAVA_VERSION
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
        sourceCompatibility = JAVA_VERSION
        targetCompatibility = JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = JAVA_TARGET
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:usecases"))
    with(Dependencies.Kotlin) {
        implementation(SERIALIZATION)
        implementation(COROUTINES_CORE)
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
