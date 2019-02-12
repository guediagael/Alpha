package ru.testTask.main

import android.content.Context

interface MainRouter {
    fun openDetails(context: Context, firstElementToShow: String?, otherElements: List<String>?)
}