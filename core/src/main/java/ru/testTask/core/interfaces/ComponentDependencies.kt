package ru.testTask.core.interfaces

interface ComponentDependencies
typealias ComponentDependenciesProvider = Map<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>
interface HasComponentDependencies {
    val dependencies: ComponentDependenciesProvider
}