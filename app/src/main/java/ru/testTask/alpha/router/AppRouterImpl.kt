package ru.testTask.alpha.router

import android.content.Context
import ru.testTask.main.MainActivity
import javax.inject.Inject

class AppRouterImpl
@Inject constructor(): AppRouter {
    override fun startFetchService(context: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun openSplash(context: Context) {

    }

    override fun openMain(context: Context) {
        MainActivity.start(context)
    }

    override fun openDetails(context: Context, firstElementToShow : String) {

    }
}