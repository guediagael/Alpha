package ru.testTask.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.testTask.common.utils.showShortToast
import ru.testTask.splash.di.SplashComponent
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject lateinit var splashPresenter: SplashContract.Presenter


    override fun onError(errorMessage: String) {
        showShortToast(errorMessage)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        SplashComponent.create(this).inject(this)
        splashPresenter.onCreate(this)

    }


    override fun onLoaded() {
        splashPresenter.onComplete(this)
        finish()
    }


}
