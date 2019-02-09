package ru.testTask.alpha.di

import dagger.MapKey
import ru.testTask.core.interfaces.ComponentDependencies
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ComponentDependenciesKey(val value: KClass<out ComponentDependencies>)