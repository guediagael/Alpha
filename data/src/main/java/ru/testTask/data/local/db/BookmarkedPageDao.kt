package ru.testTask.data.local.db

import android.arch.persistence.room.*

@Dao
interface BookmarkedPageDao {
    @Query("SELECT * FROM BookmarkedPage")
    fun getAllBookmarkedPages(): List<BookmarkedPage>

    @Query("SELECT * FROM BookmarkedPage WHERE link = :link LIMIT 1")
    fun getBookmarkedPage(link: String): BookmarkedPage

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewBookmark(vararg bookmarkedPages: BookmarkedPage)

    @Query("DELETE FROM BookmarkedPage WHERE link =:link")
    fun deletePage(link: String): Int
}