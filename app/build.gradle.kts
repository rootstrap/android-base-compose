plugins {
    id("com.android.application")
    with(Dependencies.Plugins) {
        kotlin(ANDROID)
    }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
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
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))
    implementation(project(":di"))

    with(Dependencies.Android) {
        implementation(CORE)
        implementation(APP_COMPAT)
        implementation(LIFECYCLE_RUNTIME)
        implementation(NAVIGATION_UI_RUNTIME)
        implementation(SPLASH_SCREEN)
    }
    with(Dependencies.Compose) {
        implementation(ACTIVITY)
        implementation(UI)
        implementation(UI_TOOLING_PREVIEW)
        implementation(MATERIAL3)
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
