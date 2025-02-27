// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp.compiler) apply false
    alias(libs.plugins.hilt.android.gradle.plugin) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

buildscript{
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.org.jetbrains.kotlin.kotlin.serialization)
        classpath(libs.hilt.android.gradle.plugin)
        classpath ("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${libs.versions.kspCompiler}")
    }
}