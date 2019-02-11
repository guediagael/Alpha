package ru.testTask.data.local.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface BookmarkedPageDao {
    @Query("SELECT * FROM BookmarkedPage")
    fun getAllBookmarkedPages(): List<BookmarkedPage>

    @Query("SELECT * FROM BookmarkedPage WHERE link = :link LIMIT 1")
    fun getBookmarkedPage(link: String): BookmarkedPage

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewBookmark(vararg bookmarkedPages: BookmarkedPage)
}