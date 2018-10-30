import org.gradle.kotlin.dsl.project

enum class Layers {
    MODEL, //  ∆
    DATA,  //  |
    VIEW   //  |
}

private object versions {
    const val supportLib = "1.0.0"
    const val lifecycleComponents = "2.0.0"
}

internal val sharedDependencies: DependencyConfigFunction = {
    // Language
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.2.71")
    // Testing
    "testImplementation"("junit:junit:4.12")
}

internal val modelModuleDependencies: DependencyConfigFunction = {
    // Serialization
    "implementation"("com.google.code.gson:gson:2.8.5")
}

internal val dataModuleDependencies: DependencyConfigFunction = {
    // Modules
    "implementation"(project(":model"))
    //Retrofit
    "implementation"("com.squareup.retrofit2:retrofit:2.4.0")
    "implementation"("com.squareup.retrofit2:converter-gson:2.0.1")
    //Testing
    "testImplementation"("com.squareup.okhttp3:logging-interceptor:3.11.0")
}

internal val viewModuleDependencies: DependencyConfigFunction = {
    // Modules
    "implementation"(project(":model"))
    "implementation"(project(":data"))
    // Support Library
    "implementation"("androidx.appcompat:appcompat:${versions.supportLib}")
    "implementation"("androidx.constraintlayout:constraintlayout:1.1.3")
    "implementation"("com.google.android.material:material:${versions.supportLib}")
    // Architecture Components
    "implementation"("androidx.lifecycle:lifecycle-extensions:${versions.lifecycleComponents}")
    "kapt"("androidx.lifecycle:lifecycle-compiler:${versions.lifecycleComponents}")
    //Image processing
    "implementation"("com.squareup.picasso:picasso:2.71828")
    // Instrumented Testing
    "androidTestImplementation"("androidx.test:runner:1.1.0")
    "androidTestImplementation"("androidx.test.espresso:espresso-core:3.1.0")
}
