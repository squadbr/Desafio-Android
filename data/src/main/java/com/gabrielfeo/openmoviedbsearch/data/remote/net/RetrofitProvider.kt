package com.gabrielfeo.openmoviedbsearch.data.remote.net

import com.gabrielfeo.openmoviedbsearch.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://www.omdbapi.com/"
/**
 * Expects an API key field to be present in [BuildConfig]. This constant is coupled to the module build
 * script, since the script must take care of defining this field.
 */
private const val OMDB_API_KEY = BuildConfig.OMDB_API_KEY

/**
 * Exposes a Retrofit instance configured for use with the Open Movie Database API. The lazily-instantiated
 * property is set up to use an API key on all calls.
 */
internal object RetrofitProvider {

    val retrofit: Retrofit by lazy { getNewRetrofitInstance() }

    private fun getNewRetrofitInstance() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(getNewOkHttpClient())
        .build()

    private fun getNewOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder().apply {
            addInterceptor(ApiKeyInterceptor)
            if (BuildConfig.DEBUG) addInterceptor(LoggingInterceptor)
        }
        return clientBuilder.build()
    }

    /**
     * Intercepts the Request before it's sent by Retrofit and appends to its URL an API key query
     * parameter. This applies to all calls, and rids the Retrofit interfaces from having to hardcode an
     * API key to the URLs.
     * @see OMDB_API_KEY
     * @see [com.gabrielfeo.openmoviedbsearch.data.remote.OpenMovieDb.MoviesService]
     */
    private object ApiKeyInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val oldRequest = chain.request()
            val originalUrl = oldRequest.url()
            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("apiKey", OMDB_API_KEY)
                .addQueryParameter("type", "movie")
                .build()
            val newRequest = oldRequest.newBuilder().url(newUrl).build()
            return chain.proceed(newRequest)
        }
    }

    private object LoggingInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            print(request)
            val response = chain.proceed(request)
            print(response)
            return response
        }

        private fun print(request: Request) = with(request) {
            println("-----------------------------------")
            println("REQUEST: ")
            println("URL: ${url()}")
            println("Header: ${headers()}")
            println("Body: ${body()}")
            println("-----------------------------------")
        }

        private fun print(response: Response) = with(response) {
            println("-----------------------------------")
            println("RESPONSE: ")
            println("Header: ${headers()}")
            println("Status code: ${code()} ${message()}")
            println("Body: ${body()}")
            println("-----------------------------------")
        }

    }

}