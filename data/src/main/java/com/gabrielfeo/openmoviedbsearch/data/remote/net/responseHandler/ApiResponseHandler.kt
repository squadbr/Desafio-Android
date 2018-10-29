package com.gabrielfeo.openmoviedbsearch.data.remote.net.responseHandler

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ApiResponseHandler<ApiResponseT> : Callback<ApiResponseT> {

    var onSuccessfulResponse: ((call: Call<ApiResponseT>, response: Response<ApiResponseT>) -> Unit)? = null
    var onError: ((errorMessage: String, call: Call<ApiResponseT>, throwable: Throwable?) -> Unit)? = null
    var errorMessage: String = ""

    override fun onResponse(call: Call<ApiResponseT>, response: Response<ApiResponseT>) {
        val responseSuccessful = wasResponseSuccessful(response)
        if (responseSuccessful) onSuccessfulResponse?.invoke(call, response)
        else onError?.invoke(errorMessage, call, null)
    }

    protected fun wasResponseSuccessful(response: Response<ApiResponseT>): Boolean {
        return response.isSuccessful && response.body() != null
    }

    override fun onFailure(call: Call<ApiResponseT>, throwable: Throwable) {
        onError?.invoke(errorMessage, call, throwable)
    }

}