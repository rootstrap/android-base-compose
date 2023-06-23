import Dependencies.Network.OKHTTP
import Dependencies.Network.OKHTTP_BOM
import Dependencies.Network.OKHTTP_LOGGING_INTERCEPTOR
import Dependencies.Network.RETROFIT
import Dependencies.Network.RETROFIT_KTX_CONVERTER
import Dependencies.Test.JUNIT
import Dependencies.Test.OKHTTP_MOCK_WEBSERVER

plugins {
    with(Dependencies.Plugins) {
        id(ANDROID_LIB)
        Dependencies.Versions.apply {
            kotlin(SERIALIZATION) version KOTLIN
        }
        kotlin(ANDROID)
    }
}

android {
    namespace = "com.rootstrap.data"

    with(Dependencies.ConfigData) {
        compileSdk = COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
            testInstrumentationRunner = "com.rootstrap.android.CustomTestRunner"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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
    implementation(project(":domain"))

    implementation(Dependencies.Kotlin.SERIALIZATION)
    implementation(RETROFIT)
    implementation(RETROFIT_KTX_CONVERTER)
    implementation(OKHTTP_BOM)
    implementation(OKHTTP)
    implementation(OKHTTP_LOGGING_INTERCEPTOR)

    testImplementation(OKHTTP_MOCK_WEBSERVER)
    testImplementation(JUNIT)
}
