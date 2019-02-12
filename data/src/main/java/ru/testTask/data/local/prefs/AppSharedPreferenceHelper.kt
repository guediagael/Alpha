package ru.testTask.data.local.prefs

import android.content.SharedPreferences
import ru.testTask.core.data.SharedPreferencesHelper
import javax.inject.Inject

class AppSharedPreferenceHelper @Inject constructor(private val sharedPreferences: SharedPreferences) :
    SharedPreferencesHelper {


    companion object {
        val KEY = "isUserFirstLogin"
    }

    override fun setIfNotFirstConnection() {
        sharedPreferences.edit().putBoolean(KEY, false).apply()
    }

    override fun isFirstConnection(): Boolean = sharedPreferences.getBoolean(KEY, true)

}