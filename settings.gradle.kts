rootProject.name = "Android Compose Base"
include("app")
include(":core:data", ":core:domain", ":core:usecases", ":core:di")

pluginManagement { // https://kotlinlang.org/docs/whatsnew1820.html#configure-gradle-settings
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

// Example modules:
include(":example:app", ":example:core:data", ":example:core:domain", ":example:core:usecases")
include(":tv", ":tv:domain", ":tv:data")
include(":ui")
include(":core:ui")
