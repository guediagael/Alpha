package ru.testTask.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.testTask.common.utils.findComponentDependencies
import ru.testTask.splash.di.DaggerSplashComponent
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashPresenter.SplashPresenterListener {


    companion object {
        fun start(context: Context){
            val intent  = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject lateinit var splashPresenter: SplashPresenter



    override fun onError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        DaggerSplashComponent.builder()
            .withActivity(this)
            .withDependencies(findComponentDependencies())
            .build()
            .inject(this)

        splashPresenter.onCreate(this)

    }


}
