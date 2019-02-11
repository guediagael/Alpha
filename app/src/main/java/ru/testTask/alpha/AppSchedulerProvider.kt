package ru.testTask.alpha
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

import io.reactivex.android.schedulers.AndroidSchedulers
import ru.testTask.core.rx.SchedulerProvider
import javax.inject.Inject

class AppSchedulerProvider @Inject constructor(): SchedulerProvider {
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}