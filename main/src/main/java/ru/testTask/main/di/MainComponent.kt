package ru.testTask.main.di

import dagger.BindsInstance
import dagger.Component
import ru.testTask.common.utils.findComponentDependencies
import ru.testTask.core.di.MainScope
import ru.testTask.main.MainActivity

@Component(dependencies = [MainDependencies::class], modules = [MainModule::class])
@MainScope
interface MainComponent {

    companion object {
        fun create(activity: MainActivity): MainComponent {
            return DaggerMainComponent.builder()
                .withActivity(activity)
                .withDependencies(activity.findComponentDependencies())
                .build()
        }
    }

    @Component.Builder
    interface Builder {
        fun withDependencies(dependencies: MainDependencies): Builder

        @BindsInstance
        fun withActivity(activity: MainActivity): Builder

        fun build(): MainComponent
    }

    fun inject(activity: MainActivity)
}