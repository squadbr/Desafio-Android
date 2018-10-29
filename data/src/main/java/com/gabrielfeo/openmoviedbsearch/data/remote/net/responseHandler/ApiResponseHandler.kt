package com.gabrielfeo.openmoviedbsearch.data.remote.net.responseHandler

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Handles an API Response, providing configurable function properties for error handling as well as
 * success handling. As the name implies, the context considered is that of an API. Thus, not only the HTTP
 * response codes, but also the fact that APIs return non-null bodies (even for failed responses) defines
 * whether the response was successful or not. An example of this pattern is a situation where a resource
 * isn't found: while a 404 status code can be expected, APIs often return a 200 code and state the error
 * in the response body.
 *
 * @param ApiResponseT an API response POJO, i.e. the actual type from the [Call].
 */
open class ApiResponseHandler<ApiResponseT> : Callback<ApiResponseT> {

    /**
     * Function to be called in case of a successful response. Nullable so that it's only invoked if set.
     * @see onResponse
     * @see wasResponseSuccessful
     */
    var onSuccessfulResponse: ((call: Call<ApiResponseT>, response: Response<ApiResponseT>) -> Unit)? = null
    /**
     * Function to be called in case of an error, be it an exception or an unsuccessful API response.
     * Nullable so that it's only invoked if set.
     * @see wasResponseSuccessful
     */
    var onError: ((errorMessage: String, call: Call<ApiResponseT>, throwable: Throwable?) -> Unit)? = null
    /**
     * Error message to be passed to the [onError] function.
     */
    var errorMessage: String = ""

    override fun onResponse(call: Call<ApiResponseT>, response: Response<ApiResponseT>) {
        val responseSuccessful = wasResponseSuccessful(response)
        if (responseSuccessful) onSuccessfulResponse?.invoke(call, response)
        else onError?.invoke(errorMessage, call, null)
    }

    /**
     * Defines criteria for an API response to be considered successful.
     */
    protected fun wasResponseSuccessful(response: Response<ApiResponseT>): Boolean {
        return response.isSuccessful && response.body() != null
    }

    override fun onFailure(call: Call<ApiResponseT>, throwable: Throwable) {
        onError?.invoke(errorMessage, call, throwable)
    }

}