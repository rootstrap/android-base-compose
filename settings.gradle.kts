rootProject.name = "Android Compose Base"
include("app", "domain", "data", "usecases", "di")

pluginManagement { // https://kotlinlang.org/docs/whatsnew1820.html#configure-gradle-settings
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
