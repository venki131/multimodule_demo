// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
        //val nav_version = "2.5.3"
        //classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.6.0")
        classpath ("com.android.tools.build:gradle:4.2.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    }
}

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}


