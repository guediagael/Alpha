package ru.testTask.alpha.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.testTask.core.interfaces.ComponentDependencies
import ru.testTask.details.di.DetailsDependencies
import ru.testTask.main.di.MainDependencies
import ru.testTask.splash.di.SplashDependencies

@Module
abstract class ComponentDependenciesModule {
    @Binds
    @IntoMap
    @ComponentDependenciesKey(SplashDependencies::class)
    abstract fun provideSplashDependencies(component: AppComponent): ComponentDependencies


    @Binds
    @IntoMap
    @ComponentDependenciesKey(MainDependencies::class)
    abstract fun provideMainDependencies(component: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(DetailsDependencies::class)
    abstract fun provideDetailsDependencies(component: AppComponent): ComponentDependencies



}