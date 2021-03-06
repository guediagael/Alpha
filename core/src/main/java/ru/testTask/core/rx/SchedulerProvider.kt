package ru.testTask.core.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun computation() : Scheduler

    fun ui(): Scheduler
}