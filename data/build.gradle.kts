import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import org.gradle.internal.impldep.com.amazonaws.PredefinedClientConfigurations.defaultConfig

plugins {
    id("com.android.library")
    id("kotlin-android")
}

configureDataModuleDependencies()

android {

    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(17)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }

}
