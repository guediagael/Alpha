package ru.testTask.splash.di

import dagger.Module
import dagger.Provides
import ru.testTask.splash.SplashContract
import ru.testTask.splash.SplashInteractor
import ru.testTask.splash.SplashPresenter
import ru.testTask.splash.SplashRouter

@Module
class SplashModule {

    @Provides
    fun provideSplashPresenter(splashInteractor: SplashInteractor, splashRouter: SplashRouter): SplashContract.Presenter =
        SplashPresenter(splashRouter, splashInteractor)
}