package com.gabrielfeo.openmoviedbsearch.data.remote.net.responseHandler

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ApiResponseHandler<ApiResponseT> : Callback<ApiResponseT> {

    var onResponseSuccessful: ((call: Call<ApiResponseT>, response: Response<ApiResponseT>) -> Unit)? = null
    var onError: ((errorMessage: String, call: Call<ApiResponseT>, throwable: Throwable?) -> Unit)? = null
    var errorMessage: String = ""

    protected var responseSuccessful: Boolean = false

    override fun onResponse(call: Call<ApiResponseT>, response: Response<ApiResponseT>) {
        with(response) { responseSuccessful = isSuccessful && body() != null }
        if (responseSuccessful) onResponseSuccessful?.invoke(call, response)
        else onError?.invoke(errorMessage, call, null)
    }

    override fun onFailure(call: Call<ApiResponseT>, throwable: Throwable) {
        onError?.invoke(errorMessage, call, throwable)
    }

}