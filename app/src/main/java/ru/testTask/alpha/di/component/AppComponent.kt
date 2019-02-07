package ru.testTask.alpha.di.component

import dagger.Component
import ru.testTask.alpha.NewsFeedShowerApp
import ru.testTask.alpha.di.module.AppModule

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app : NewsFeedShowerApp)
}