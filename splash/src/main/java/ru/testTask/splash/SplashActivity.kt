package ru.testTask.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.testTask.common.utils.findComponentDependencies
import ru.testTask.splash.di.DaggerSplashComponent

class SplashActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context){
            val intent  = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        DaggerSplashComponent.builder()
            .withActivity(this)
            .withDependcies(findComponentDependencies())
            .build()
            .inject(this)
    }
}
