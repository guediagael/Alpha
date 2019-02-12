package ru.testTask.details.di

import dagger.BindsInstance
import dagger.Component
import ru.testTask.common.utils.findComponentDependencies
import ru.testTask.core.di.MainScope
import ru.testTask.details.DetailsActivity

@Component(dependencies = [DetailsDependencies::class], modules = [DetailsModule::class])
@MainScope
interface DetailsComponent {

    companion object {
        fun create(activity: DetailsActivity): DetailsComponent {
            return DaggerDetailsComponent.builder()
                .withActivity(activity)
                .withDependencies(activity.findComponentDependencies())
                .build()
        }
    }

    @Component.Builder
    interface Builder {
        fun withDependencies(dependencies: DetailsDependencies): Builder

        @BindsInstance
        fun withActivity(activity: DetailsActivity): Builder

        fun build(): DetailsComponent
    }

    fun inject(activity: DetailsActivity)
}