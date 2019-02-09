package ru.testTask.alpha

import android.app.Application
import ru.testTask.alpha.di.AppModule
import ru.testTask.alpha.di.DaggerAppComponent
import ru.testTask.core.interfaces.ComponentDependenciesProvider
import ru.testTask.core.interfaces.HasComponentDependencies
import javax.inject.Inject

open class NewsFeedShowerApp: Application(), HasComponentDependencies {

    @Inject
    override lateinit var  dependencies: ComponentDependenciesProvider
        protected set

    override fun onCreate() {
        super.onCreate()
        setup()
    }

    fun setup(){
        val appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this)).build()
        appComponent.inject(this)
    }

}