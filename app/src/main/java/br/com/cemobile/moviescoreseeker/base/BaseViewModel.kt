package br.com.cemobile.moviescoreseeker.base

import android.arch.lifecycle.*
import android.support.annotation.VisibleForTesting
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private var jobContext: CoroutineContext = Dispatchers.Main
    private val jobList = mutableListOf<Job>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Throwable>()


    fun runJob(block: suspend CoroutineScope.() -> Unit) {
        val job = GlobalScope.launch(jobContext) {
            block()
        }
        jobList.add(job)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun clearJobs() {
        jobList.forEach { it.cancel() }
        jobList.clear()
    }

    @VisibleForTesting
    fun enableUnitTest() {
        jobContext = Dispatchers.Unconfined
    }

}