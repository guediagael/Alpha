package ru.testTask.alpha.di

import dagger.Component
import ru.testTask.alpha.NewsFeedShowerApp
import ru.testTask.core.di.ApplicationScope
import ru.testTask.data.di.ApiModule
import ru.testTask.data.di.RepoModule
import ru.testTask.details.di.DetailsDependencies
import ru.testTask.main.di.MainDependencies
import ru.testTask.splash.di.SplashDependencies

@ApplicationScope
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        RouterModule::class,
        RepoModule::class,
        ComponentDependenciesModule::class
    ]
)
interface AppComponent: MainDependencies, SplashDependencies, DetailsDependencies {
    fun inject(app: NewsFeedShowerApp)
}