package ru.testTask.core.data

interface SharedPreferencesHelper {
    fun isFirstConnection():Boolean
    fun setIfNotFirstConnection()
}