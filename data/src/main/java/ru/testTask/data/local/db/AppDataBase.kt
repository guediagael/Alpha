package ru.testTask.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.testTask.data.BuildConfig

@Database(entities = [BookmarkedPage::class, NewsItem::class], version = BuildConfig.DB_VERSION, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun bookmarkedPageDao(): BookmarkedPageDao

    abstract fun newsItemDao(): NewsItemDao
}