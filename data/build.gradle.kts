import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import com.android.build.gradle.internal.dsl.BaseFlavor
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
        setApiKeyField()
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

fun BaseFlavor.setApiKeyField() {
    val EXT_OMDB_API_KEY: Any? by project
    val apiKeyPropValue = "$EXT_OMDB_API_KEY"
    val apiKeyFieldName = "OMDB_API_KEY"
    buildConfigField("String", apiKeyFieldName, "\"$apiKeyPropValue\"")
}