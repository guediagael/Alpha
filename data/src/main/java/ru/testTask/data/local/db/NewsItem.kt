package ru.testTask.data.local.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class NewsItem(
    @PrimaryKey var url: String,
    @ColumnInfo(name = "title") var title: String?
)