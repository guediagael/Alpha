package ru.testTask.splash.di

import dagger.BindsInstance
import dagger.Component
import ru.testTask.core.di.MainScope
import ru.testTask.splash.SplashActivity

@Component(dependencies = [ SplashDependencies::class], modules = [SplashModule::class])
@MainScope
interface SplashComponent {
    @Component.Builder
    interface Builder {
        fun  withDependencies(dependencies: SplashDependencies): Builder

        @BindsInstance
        fun withActivity(activity: SplashActivity): Builder

        fun  build(): SplashComponent
    }

    fun inject(activity: SplashActivity)
}