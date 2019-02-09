package ru.testTask.alpha.di

import dagger.Binds
import dagger.Module
import ru.testTask.alpha.router.AppRouterImpl
import ru.testTask.core.di.ApplicationScope
import ru.testTask.main.MainRouter
import ru.testTask.splash.SplashRouter

@Module
abstract class RouterModule {

    @Binds
    @ApplicationScope
    abstract fun bindMainRouter(router: AppRouterImpl): MainRouter

    @Binds
    @ApplicationScope
    abstract fun bindSplashRouter(router: AppRouterImpl): SplashRouter

}