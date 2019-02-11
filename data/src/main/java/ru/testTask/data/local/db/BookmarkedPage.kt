package ru.testTask.data.local.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class BookmarkedPage(
    @PrimaryKey var link: String,
    @ColumnInfo(name = "html") var html: String?
)