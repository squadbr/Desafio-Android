import org.gradle.kotlin.dsl.project

internal val sharedDependencies: DependencyConfigFunction = {
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.2.71")
    "implementation"("com.google.code.gson:gson:2.8.5")
}

internal val modelModuleDependencies: DependencyConfigFunction = {

}

internal val dataModuleDependencies: DependencyConfigFunction = {
    "implementation"(project(":model"))
}

internal val viewModuleDependencies: DependencyConfigFunction = {
    "implementation"(project(":model"))
    "implementation"(project(":data"))
    "implementation"("com.android.support:appcompat-v7:28.0.0")
    "implementation"("com.android.support.constraint:constraint-layout:1.1.3")
    "testImplementation"("junit:junit:4.12")
    "androidTestImplementation"("com.android.support.test:runner:1.0.2")
    "androidTestImplementation"("com.android.support.test.espresso:espresso-core:3.0.2")
}
