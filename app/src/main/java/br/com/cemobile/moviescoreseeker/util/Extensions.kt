package br.com.cemobile.moviescoreseeker.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import br.com.cemobile.moviescoreseeker.R
import br.com.cemobile.moviescoreseeker.core.GlideApp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.experimental.Deferred
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    try {
        if (imageUrl != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(this)
        } else {
            this.setImageResource(R.drawable.no_image)
        }
    } catch (e: Throwable) {
        e.printStackTrace()
    }
}

class NetworkException : Exception {
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String? = null,
                cause: Throwable? = null,
                enableSuppression: Boolean = false,
                writableStackTrace: Boolean = false
    ) : super(message, cause, enableSuppression, writableStackTrace)
}

suspend fun <T> Deferred<T>.execute(): T {
    return try {
        await()
    } catch (t: Throwable) {
        when (t) {
            is UnknownHostException,
            is SocketTimeoutException -> throw NetworkException(t)
            else -> throw t
        }
    }

}
