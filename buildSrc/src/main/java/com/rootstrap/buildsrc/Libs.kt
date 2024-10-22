import org.gradle.api.JavaVersion

object Dependencies {
    object Android {
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val CORE = "androidx.core:core-ktx:${Versions.CORE}"
        const val SPLASH_SCREEN = "androidx.core:core-splashscreen:${Versions.SPLASH_SCREEN}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
        const val LEGACY_SUPPORT_V4 =
            "androidx.legacy:legacy-support-v4:${Versions.LEGACY_SUPPORT_V4}"
        const val PREFERENCE = "androidx.preference:preference-ktx:${Versions.PREFERENCE}"
        const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLERVIEW}"
        const val WORK_RUNTIME = "androidx.work:work-runtime-ktx:${Versions.WORK_RUNTIME}"
        const val WINDOW = "androidx.window:window:${Versions.WINDOW}"

        // Lifecycle
        const val LIFECYCLE_COMMON =
            "android.arch.lifecycle:common-java8:${Versions.LIFECYCLE_COMMON}"
        const val LIFECYCLE_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
        const val LIFECYCLE_COMPOSE =
            "androidx.lifecycle:lifecycle-runtime-compose:${Versions.LIFECYCLE}"
        const val LIFECYCLE_LIVEDATA =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
        const val LIFECYCLE_EXTENSIONS =
            "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE_EXTENSION}"
        const val LIFECYCLE_VIEW_MODEL =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"

        // Navigation
        const val NAVIGATION_FRAGMENT =
            "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI_RUNTIME =
            "androidx.navigation:navigation-runtime-ktx:${Versions.NAVIGATION_RUN_TIME}"

        // Room
        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"

        // DataStore
        const val DATA_STORE_PREFERENCES =
            "androidx.datastore:datastore-preferences:${Versions.DATA_STORE_PREFERENCES}"

    }

    object Compose {
        const val ACTIVITY = "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY}"
        const val UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_TOOLING}"
        const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_TOOLING}"
        const val MATERIAL3 = "androidx.compose.material3:material3:${Versions.COMPOSE_MATERIAL3}"
        const val ICONS_EXTENDED =
            "androidx.compose.material:material-icons-extended:${Versions.COMPOSE}"
        const val NAVIGATION_COMPOSE =
            "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION}"
        const val LIFECYCLE_RUNTIME =
            "androidx.lifecycle:lifecycle-runtime-compose:${Versions.LIFECYCLE}"
        const val COIL = "io.coil-kt:coil-compose:${Versions.COIL}"
    }

    object Common {
        // Image
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"

        // MixPanel
        const val MIX_PANEL = "com.mixpanel.android:mixpanel-android:${Versions.MIX_PANEL}"

        // Events
        const val OTTO = "com.squareup:otto:${Versions.OTTO}"

        // Security crypto
        const val SECURITY_CRYPTO = "androidx.security:security-crypto:${Versions.SECURITY_CRYPTO}"

        val JAVA_VERSION = JavaVersion.VERSION_17
        val JAVA_TARGET = "17"
    }

    object Rootstrap {
        const val FLOW_FORMS = "com.github.rootstrap.FlowForms:FlowForms-Core:${Versions.FLOW_FORMS}"
    }

    object Tv {
        const val TV_FOUNDATION = "androidx.tv:tv-foundation:${Versions.COMPOSE_TV}"
        const val TV_MATERIAL = "androidx.tv:tv-material:${Versions.COMPOSE_TV}"
        const val LEANBACK = "androidx.leanback:leanback:${Versions.LEANBACK}"
    }

    object MEDIA {
        const val MEDIA_3 = "androidx.media3:media3-exoplayer:${Versions.MEDIA_3}"
        const val MEDIA_3_UI = "androidx.media3:media3-ui:${Versions.MEDIA_3}"
        const val MEDIA_3_SESSION = "androidx.media3:media3-session:${Versions.MEDIA_3}"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_DESIGN}"
        const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
        const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
        const val FIREBASE_CORE = "com.google.firebase:firebase-core"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
        const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
    }

    object Kapt { // TODO migrate to KSP https://developer.android.com/build/migrate-to-ksp
        const val GLIDE_KAPT = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
        const val LIFECYCLE_KAPT = "android.arch.lifecycle:compiler:${Versions.LIFECYCLE_COMMON}"
        const val ROOM_KAPT = "androidx.room:room-compiler:${Versions.ROOM}"
    }

    object Koin {
        const val CORE = "io.insert-koin:koin-core:${Versions.KOIN}"
        const val ANDROID = "io.insert-koin:koin-android:${Versions.KOIN}"
        const val ANDROID_NAVIGATION = "io.insert-koin:koin-androidx-navigation:${Versions.KOIN}"
        const val ANDROID_COMPAT = "io.insert-koin:koin-android-compat:${Versions.KOIN}"
        const val WORK_MANAGER = "io.insert-koin:koin-androidx-workmanager:${Versions.KOIN}"
        const val COMPOSE = "io.insert-koin:koin-androidx-compose:${Versions.KOIN}"
    }

    object Kotlin {
        const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
        const val COROUTINES_CORE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_CORE}"
        const val COROUTINES_ANDROID =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
        const val REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}"
        const val SERIALIZATION =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.SERIALIZATION_JSON}"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
        const val RETROFIT_KTX_CONVERTER =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.RETROFIT_KTX_CONVERTER}"
        const val LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_BOM}"
        const val OKHTTP_BOM = "com.squareup.okhttp3:okhttp-bom:${Versions.OKHTTP_BOM}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp"
        const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor"
    }

    // Linters
    object Linters {
        const val KT_LINT = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.KLINT_GRADLE}"
        const val KT_LINT_PLUGIN = "org.jlleitschuh.gradle.ktlint"
    }

    object Test {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"

        const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
        const val OKHTTP_MOCK_WEBSERVER =
            "com.squareup.okhttp3:mockwebserver:${Versions.OKHTTP_BOM}"
        const val COROUTINES_TEST =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"
        const val ANDROID_CORE_TESTING = "android.arch.core:core-testing:${Versions.CORE_TESTING}"

        const val KOIN_TEST = "io.insert-koin:koin-test:${Versions.KOIN}"
        const val KOIN_TEST_JUNIT = "io.insert-koin:koin-test-junit4:${Versions.KOIN}"
    }

    object AndroidTest {
        const val MOCKK_ANDROID = "io.mockk:mockk-android:${Versions.MOCKK}"
        const val TEST_RUNNER = "androidx.test:runner:${Versions.TEST_RUNNER}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
        const val ESPRESSO_INTENTS = "androidx.test.espresso:espresso-intents:${Versions.ESPRESSO}"
        const val EXT_JUNIT = "androidx.test.ext:junit:${Versions.EXT_JUNIT}"
        const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
        const val COMPOSE_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE}"
        const val UI_AUTOMATOR = "androidx.test.uiautomator:uiautomator:${Versions.UI_AUTOMATOR}"
        const val RULES = "androidx.test:rules:${Versions.RULES}"
        const val MOCK_WEBSERVER = "com.squareup.okhttp3:mockwebserver:${Versions.MOCK_WEBSERVER}"
    }

    object ClassPaths {
        const val GRADLE = "com.android.tools.build:gradle:8.0.2"
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
        const val GMS = "com.google.gms:google-services:${Versions.GOOGLE_SERVICES}"
        const val FIREBASE_CRASHLYTICS_GRADLE =
            "com.google.firebase:firebase-crashlytics-gradle:${Versions.FIREBASE_CRASHLYTICS_GRADLE}"
    }

    object Versions {
        const val APP_COMPAT = "1.6.1"
        const val CONSTRAINT_LAYOUT = "2.1.1"
        const val COMPOSE = "1.4.0"

        const val COMPOSE_ACTIVITY = "1.8.0"
        const val COMPOSE_MATERIAL3 = "1.1.1"
        const val COMPOSE_NAVIGATION = "2.8.2"
        const val COMPOSE_TOOLING = "1.6.0-alpha08"
        const val CONVERTER_GSON = "2.9.0"
        const val CONVERTER_MOSHI = "2.5.0"
        const val CORE = "1.6.0"
        const val COROUTINES = "1.5.1"
        const val COROUTINES_CORE = "1.9.0"
        const val SERIALIZATION_JSON = "1.7.3"
        const val CORE_TESTING = "1.1.1"
        const val ESPRESSO = "3.4.0"
        const val EXT_JUNIT = "1.1.3"
        const val FIREBASE_BOM = "26.1.0"
        const val FIREBASE_CRASHLYTICS_GRADLE = "2.8.1"
        const val GLIDE = "4.12.0"
        const val GOOGLE_SERVICES = "4.3.10"
        const val GSON = "2.8.7"
        const val JUNIT = "4.13.2"
        const val KOIN = "4.0.0"
        const val KOTLIN = "2.0.0"
        const val KT_LINT = "0.44.0"
        const val LEGACY_SUPPORT_V4 = "1.0.0"
        const val LIFECYCLE = "2.6.1"
        const val LIFECYCLE_COMMON = "1.1.1"
        const val LIFECYCLE_EXTENSION = "2.2.0"
        const val MATERIAL_DESIGN = "1.4.0"
        const val MIX_PANEL = "5.6.1"
        const val MOCKK = "1.12.0"
        const val MOCK_WEBSERVER = "4.9.0"
        const val MOSHI = "1.12.0"
        const val NAVIGATION = "2.3.5"
        const val NAVIGATION_RUN_TIME = "2.5.3"
        const val OKHTTP_BOM = "4.10.0"
        const val OTTO = "1.3.8"
        const val PREFERENCE = "1.1.1"
        const val RECYCLERVIEW = "1.1.0"
        const val RETROFIT = "2.11.0"
        const val RETROFIT_KTX_CONVERTER = "1.0.0"
        const val ROOM = "2.4.1"
        const val DATA_STORE_PREFERENCES = "1.0.0"
        const val RULES = "1.4.0"
        const val SECURITY_CRYPTO = "1.1.0-alpha03"
        const val TEST_RUNNER = "1.4.0"
        const val UI_AUTOMATOR = "2.2.0"
        const val WORK_RUNTIME = "2.7.0"
        const val KLINT_GRADLE = "11.5.1"
        const val SPLASH_SCREEN = "1.0.0"
        const val COMPOSE_TV = "1.0.0-alpha10"
        const val LEANBACK = "1.2.0-alpha03"
        const val COIL = "2.5.0"
        const val MEDIA_3 = "1.2.0-rc01"
        const val WINDOW = "1.1.0"
        const val FLOW_FORMS = "1.4.1"
    }

    object ConfigData {
        const val COMPILE_SDK_VERSION = 34
        const val BUILD_TOOLS_VERSION = "31.0.0"
        const val MIN_SDK_VERSION = 24
        const val TARGET_SDK_VERSION = 34
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0"
    }

    object Plugins {
        const val ANDROID = "android"
        const val ANDROID_LIB = "com.android.library"
        const val SERIALIZATION = "plugin.serialization"
        const val CRASHLYTICS = "com.google.firebase.crashlytics"
        const val GOOGLE_SERVICES = "com.google.gms.google-services"
        const val KAPT = "kapt"
        const val JAVA_LIBRARY = "java-library"
        const val KOTLIN_JVM = "org.jetbrains.kotlin.jvm"
        const val COMPOSE = "org.jetbrains.kotlin.plugin.compose"
        const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlin.plugin.serialization"
    }
}
