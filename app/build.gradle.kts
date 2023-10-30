import Dependencies.Common.JAVA_TARGET
import Dependencies.Common.JAVA_VERSION

plugins {
    id("com.android.application")
    kotlin(Dependencies.Plugins.ANDROID)
}

android {
    namespace = "com.rootstrap"

    with(Dependencies.ConfigData) {
        compileSdk = COMPILE_SDK_VERSION
        buildToolsVersion = BUILD_TOOLS_VERSION

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

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Versions.COMPOSE_COMPILER
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:usecases"))
    implementation(project(":core:di"))

    with(Dependencies.Android) {
        implementation(CORE)
        implementation(APP_COMPAT)
        implementation(LIFECYCLE_KTX)
        implementation(LIFECYCLE_COMPOSE)
        implementation(NAVIGATION_UI_RUNTIME)
        implementation(SPLASH_SCREEN)
        implementation(WINDOW)
    }
    with(Dependencies.Compose) {
        implementation(ACTIVITY)
        implementation(UI)
        implementation(UI_TOOLING_PREVIEW)
        implementation(MATERIAL3)
        implementation(ICONS_EXTENDED)
        implementation(NAVIGATION_COMPOSE)
        implementation(LIFECYCLE_RUNTIME)
        debugImplementation(UI_TOOLING)
    }
    implementation(Dependencies.Koin.CORE)
    implementation(Dependencies.Koin.ANDROID)

    with(Dependencies.Test) {
        testImplementation(MOCKK)
        testImplementation(JUNIT)
        testImplementation(KOIN_TEST)
        testImplementation(KOIN_TEST_JUNIT)
        testImplementation(COROUTINES_TEST)
    }
    with(Dependencies.AndroidTest) {
        androidTestImplementation(EXT_JUNIT)
        androidTestImplementation(ESPRESSO_CORE)
        androidTestImplementation(COMPOSE_JUNIT)
        debugImplementation(COMPOSE_MANIFEST)
    }
}
