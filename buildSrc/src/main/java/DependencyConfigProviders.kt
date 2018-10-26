import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies

/**
 * Wraps the dependency configuration blocks to be run on the `model` module.
 * @see configure
 * @see DependencyConfigFunction
 */
fun Project.configureModelModuleDependencies() = configure(
    sharedDependencies,
    modelModuleDependencies
)

/**
 * Wraps the dependency configuration blocks to be run on the `data` module.
 * @see configure
 * @see DependencyConfigFunction
 */
fun Project.configureDataModuleDependencies() = configure(
    sharedDependencies,
    dataModuleDependencies
)

/**
 * Wraps the dependency configuration blocks to be run on the `view` module.
 * @see configure
 * @see DependencyConfigFunction
 */
fun Project.configureViewModuleDependencies() = configure(
    sharedDependencies,
    viewModuleDependencies
)

/**
 * Passes all provided [DependencyHandlerScope]`.() -> Unit` functions to the [dependencies]
 * function. Aims for better readability at the call site.
 */
internal fun Project.configure(vararg dependencyConfigBlocks: DependencyConfigFunction) =
    dependencyConfigBlocks.forEach { block -> dependencies(block) }

/**
 * Simple `typealias` for improved readability.
 */
internal typealias DependencyConfigFunction = DependencyHandlerScope.() -> Unit