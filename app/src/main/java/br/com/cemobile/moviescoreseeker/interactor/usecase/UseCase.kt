package br.com.cemobile.moviescoreseeker.interactor.usecase

import android.util.Log
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a coroutine
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class UseCase<T> {

    protected var parentJob: Job = Job()
    open var backgroundContext: CoroutineContext = Dispatchers.Default
    open var foregroundContext: CoroutineContext = Dispatchers.Main


    protected abstract suspend fun executeOnBackground(): T

    fun execute(onComplete: (T) -> Unit, onError: (Throwable) -> Unit) {
        parentJob.cancel()
        parentJob = Job()
        GlobalScope.launch(foregroundContext + parentJob, CoroutineStart.DEFAULT, null, {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground()
                }
                onComplete.invoke(result)
            } catch (e: CancellationException) {
                Log.d("UseCase", "canceled by user")
            } catch (e: Exception) {
                onError(e)
            }
        })
    }

    protected suspend fun <X> background(context: CoroutineContext = backgroundContext, block: suspend () -> X): Deferred<X> {
        return GlobalScope.async(context + parentJob, CoroutineStart.DEFAULT, null, {
            block.invoke()
        })
    }

    fun unsubscribe() {
        parentJob.cancel()
    }

}