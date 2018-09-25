package br.com.cemobile.moviescoreseeker.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MoviesServiceFactory {

    private const val BASE_URL = "http://www.omdbapi.com/?apikey=3c4692c2&type=movie"
    private const val DEFAULT_TIMEOUT_SECONDS = 15L
    private const val API_KEY_PARAM_NAME = "apikey"
    private const val API_KEY_PARAM_VALUE = "3c4692c2"
    private const val TYPE_PARAM_NAME = "type"
    private const val MOVIE_PARAM_VALUE = "movie"

    fun create(apiURL: String = BASE_URL, debuggable: Boolean = false): MoviesServices {
        val logger = createLogger(debuggable)
        val httpClient = createHttpClient(logger = logger)
        val converter = GsonConverterFactory.create()
        val coroutineAdapter = CoroutineCallAdapterFactory()

        val retrofit = Retrofit.Builder()
                .baseUrl(apiURL)
                .client(httpClient)
                .addConverterFactory(converter)
                .addCallAdapterFactory(coroutineAdapter)
                .build()

        return retrofit.create(MoviesServices::class.java)
    }

    private fun createLogger(debuggable: Boolean): Interceptor {
        val loggingLevel = if (debuggable) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().apply { level = loggingLevel }
    }

    private fun authorizer() = Interceptor { chain ->
        val request = chain.request()
        val requestUrl = request.url()

        val authorizedUrl = requestUrl.newBuilder()
                .addQueryParameter(API_KEY_PARAM_NAME, API_KEY_PARAM_VALUE)
                .addQueryParameter(TYPE_PARAM_NAME, MOVIE_PARAM_VALUE)
                .build()

        val authorized = request.newBuilder()
                .url(authorizedUrl)
                .build()

        chain.proceed(authorized)
    }

    private fun createHttpClient(logger: Interceptor) =
        OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(authorizer())
                .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build()

}