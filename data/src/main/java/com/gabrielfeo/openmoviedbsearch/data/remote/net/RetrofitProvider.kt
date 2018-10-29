package com.gabrielfeo.openmoviedbsearch.data.remote.net

import com.gabrielfeo.openmoviedbsearch.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://www.omdbapi.com/"
private const val OMDB_API_KEY = BuildConfig.OMDB_API_KEY

internal object RetrofitProvider {

    val retrofit: Retrofit by lazy { getNewRetrofitInstance() }

    private fun getNewRetrofitInstance() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(getNewOkHttpClient())
        .build()

    private fun getNewOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor)
        .build()

    object ApiKeyInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val oldRequest = chain.request()
            val originalUrl = oldRequest.url()
            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("apiKey", OMDB_API_KEY)
                .build()
            val newRequest = oldRequest.newBuilder().url(newUrl).build()
            return chain.proceed(newRequest)
        }
    }

}