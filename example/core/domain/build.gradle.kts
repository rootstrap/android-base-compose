import Dependencies.Common.JAVA_TARGET
import Dependencies.Common.JAVA_VERSION

plugins {
    with(Dependencies.Plugins) {
        id(ANDROID_LIB)
        kotlin(ANDROID)
    }
}

android {
    namespace = "com.rootstrap.example.domain"

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
        sourceCompatibility = JAVA_VERSION
        targetCompatibility = JAVA_VERSION
    }

    kotlinOptions {
        jvmTarget = JAVA_TARGET
    }
}

dependencies {
}
