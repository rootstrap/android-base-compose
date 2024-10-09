import Dependencies.Common.JAVA_VERSION
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    kotlin(Dependencies.Plugins.ANDROID)
    id(Dependencies.Plugins.COMPOSE) version Dependencies.Versions.KOTLIN
    id(Dependencies.Plugins.KOTLIN_SERIALIZATION) version Dependencies.Versions.KOTLIN
}

android {
    namespace = "com.rootstrap.example.app"

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

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.addAll(
                listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + project.buildDir.absolutePath + "/compose_metrics"
                )
            )
            freeCompilerArgs.addAll(
                listOf(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + project.buildDir.absolutePath + "/compose_metrics"
                )
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:di"))
    implementation(project(":core:domain"))
    implementation(project(":example:core:domain"))
    implementation(project(":example:core:usecases"))

    with(Dependencies.Kotlin) {
        implementation(SERIALIZATION)
    }

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
    with(Dependencies.Koin) {
        implementation(CORE)
        implementation(ANDROID)
        implementation(ANDROID_NAVIGATION)
        implementation(ANDROID_COMPAT)
        implementation(WORK_MANAGER)
        implementation(COMPOSE)
    }
    implementation(Dependencies.Rootstrap.FLOW_FORMS)

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
