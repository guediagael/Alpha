package ru.testTask.data.local.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface NewsItemDao {
    @Query("SELECT * FROM NewsItem")
    fun getNewsFeed(): List<NewsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewItems( newsItems: List<NewsItem>)
}