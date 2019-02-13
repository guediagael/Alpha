package ru.testTask.alpha.router

import android.content.Context
import android.util.Log
import ru.testTask.details.DetailsActivity
import ru.testTask.main.MainActivity
import javax.inject.Inject

class AppRouterImpl
@Inject constructor(): AppRouter {




    override fun openSplash(context: Context) {

    }

    override fun openMain(context: Context) {
        MainActivity.start(context)
    }

    override fun openDetails(context: Context, firstElementToShow : String?, otherElements: List<String>? ) {
        Log.i("opending detailss...", ".....")
        val arrayListOfString = ArrayList<String>()
        otherElements?.asIterable()?.let { arrayListOfString.addAll(it) }
        DetailsActivity.start(context,arrayListOfString, firstElementToShow)
    }


}