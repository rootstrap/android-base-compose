// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.ClassPaths.GRADLE)
        classpath(Dependencies.ClassPaths.KOTLIN_GRADLE_PLUGIN)
        classpath(Dependencies.Linters.KT_LINT)
    }
}

plugins {
    id(Dependencies.Linters.KT_LINT_PLUGIN) version (Dependencies.Versions.KLINT_GRADLE)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    apply(plugin = Dependencies.Linters.KT_LINT_PLUGIN)
}

ktlint {
    debug.set(true)
    verbose.set(true)
    android.set(true)
    outputToConsole.set(true)
    ignoreFailures.set(false)
}
