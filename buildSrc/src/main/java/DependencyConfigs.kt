import org.gradle.kotlin.dsl.project

enum class Layers {
    MODEL, //  ∆
    DATA,  //  |
    VIEW   //  |
}

private object versions {
    const val supportLib = "28.0.0"
    const val lifecycleComponents = "1.1.1"
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
    // Architecture Components
    "implementation"("android.arch.lifecycle:extensions:${versions.lifecycleComponents}")
    "implementation"("android.arch.lifecycle:runtime:${versions.lifecycleComponents}")
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
    "implementation"("com.android.support:appcompat-v7:${versions.supportLib}")
    "implementation"("com.android.support.constraint:constraint-layout:1.1.3")
    "implementation"("com.android.support:design:${versions.supportLib}")
    //Image processing
    "implementation"("com.squareup.picasso:picasso:2.71828")
    // Instrumented Testing
    "androidTestImplementation"("com.android.support.test:runner:1.0.2")
    "androidTestImplementation"("com.android.support.test.espresso:espresso-core:3.0.2")
}
