import Dependencies.Common.JAVA_TARGET
import Dependencies.Common.JAVA_VERSION
import Dependencies.Test.JUNIT

plugins {
    with(Dependencies.Plugins) {
        id(ANDROID_LIB)
        kotlin(ANDROID)
    }
}

android {
    namespace = "com.rootstrap.example.usecases"
    with(Dependencies.ConfigData) {
        compileSdk = COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    buildTypes {
        getByName("release") {
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
    implementation(project(":example:core:data"))
    implementation(project(":example:core:domain"))

    testImplementation(JUNIT)
}
