package ru.testTask.splash.di

import dagger.BindsInstance
import dagger.Component
import ru.testTask.common.utils.findComponentDependencies
import ru.testTask.core.di.MainScope
import ru.testTask.splash.SplashActivity

@Component(dependencies = [ SplashDependencies::class], modules = [SplashModule::class])
@MainScope
interface SplashComponent {

    companion object {
        fun create(activity: SplashActivity): SplashComponent {
            return DaggerSplashComponent.builder()
                .withActivity(activity)
                .withDependencies(activity.findComponentDependencies())
                .build()
        }
    }

    @Component.Builder
    interface Builder {
        fun  withDependencies(dependencies: SplashDependencies): Builder

        @BindsInstance
        fun withActivity(activity: SplashActivity): Builder

        fun  build(): SplashComponent
    }

    fun inject(activity: SplashActivity)
}