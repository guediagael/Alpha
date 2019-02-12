package ru.testTask.data.di

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.testTask.core.data.DataManager
import ru.testTask.core.data.DbHelper
import ru.testTask.core.data.SharedPreferencesHelper
import ru.testTask.core.data.repo.FetchDataRepo
import ru.testTask.core.data.repo.LoadDataRepo
import ru.testTask.core.di.ApplicationScope
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.data.AppDataManager
import ru.testTask.data.AppRepoImpl
import ru.testTask.data.BuildConfig
import ru.testTask.data.local.db.AppDataBase
import ru.testTask.data.local.db.AppDbHelper
import ru.testTask.data.local.prefs.AppSharedPreferenceHelper
import ru.testTask.data.remote.api.FeedApi

@Module
class RepoModule {

    @Provides
    @ApplicationScope
    fun provideDatabase(application: Context): AppDataBase {
        val db = Room
            .databaseBuilder(application, AppDataBase::class.java, BuildConfig.APPLICATION_ID)
            .allowMainThreadQueries()
            .build()
        return db
    }



    @ApplicationScope
    @Provides
    fun provideSharedPreferenceHelper(context: Context): SharedPreferencesHelper {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
        return AppSharedPreferenceHelper(sharedPreferences)
    }

    @ApplicationScope
    @Provides
    fun provideDbHelper(appDataBase: AppDataBase): DbHelper = AppDbHelper(appDataBase)



    @Provides
    @ApplicationScope
    fun provideDataManager(sharedPreferencesHelper: SharedPreferencesHelper, dbHelper: DbHelper): DataManager{
        return AppDataManager(sharedPreferencesHelper, dbHelper)
    }

    @Provides
    @ApplicationScope
    fun provideSplashRepo(feedApi: FeedApi, dataManager: DataManager, schedulerProvider: SchedulerProvider): FetchDataRepo {
        return AppRepoImpl(feedApi, dataManager, schedulerProvider)
    }


    @Provides
    @ApplicationScope
    fun provideMainRepo(feedApi: FeedApi, dataManager: DataManager, schedulerProvider: SchedulerProvider): LoadDataRepo = AppRepoImpl(feedApi, dataManager, schedulerProvider)
}