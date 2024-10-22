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
        maven { setUrl("https://jitpack.io") }
    }
}

// task that copy the pre-commit hook to .git/hooks
// this task is executed when gradle is executed
// this task is executed only if the pre-commit hook does not exist
tasks.register<Copy>("copyGitHooks") {
    val preCommitHook = ".git/hooks/pre-commit"
    val preCommitHookFile = File(preCommitHook)
    if (!preCommitHookFile.exists()) {
        from("pre-commit")
        into(".git/hooks")
        rename("pre-commit.sample", "pre-commit")
        preCommitHookFile.setExecutable(true)
    }
}

// task that runs ktlint on all kotlin files
// this task is executed only from the pre-commit hook
tasks.register("preCommit") {
    dependsOn("ktlint")
    doLast {
        exec {
            commandLine("ktlintCheck", "app/src/**/*.kt")
            commandLine("ktlintCheck", "core/**/*.kt")
            // Uncomment this line to run linter on example folder
            // commandLine("ktlintCheck", "example/**/*.kt")
        }
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
