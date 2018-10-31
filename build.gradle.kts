// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.2.1")
        classpath(kotlin("gradle-plugin", version = "1.2.71"))
    }

}

allprojects {

    repositories {
        google()
        jcenter()
        mavenCentral()
    }

}

