import Dependencies.Common.JAVA_VERSION
import Dependencies.Kotlin.COROUTINES_CORE

plugins {
    id(Dependencies.Plugins.JAVA_LIBRARY)
    id(Dependencies.Plugins.KOTLIN_JVM)
}

java {
    sourceCompatibility = JAVA_VERSION
    targetCompatibility = JAVA_VERSION
}

dependencies {
    implementation(COROUTINES_CORE)
}
