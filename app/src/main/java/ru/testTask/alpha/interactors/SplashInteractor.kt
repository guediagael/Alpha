package ru.testTask.alpha.interactors

import ru.testTask.alpha.data.model.Item

open class SplashInteractor {
    companion object {
        private val TAG: String = SplashInteractor::class.java.simpleName
    }

    interface OnArticlesLoadedListener {
        fun onSuccess( items : List<Item>)
        fun onError( errorMessage : String)
    }


    fun fetchArticles(){

    }
}