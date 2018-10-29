package com.gabrielfeo.openmoviedbsearch.data.remote.net.responseHandler

import com.gabrielfeo.openmoviedbsearch.model.response.SearchResponse
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response

class SearchResponseHandler<ApiResponseT, SearchResultsT> : ApiResponseHandler<ApiResponseT>()
        where ApiResponseT : SearchResponse<SearchResultsT> {

    var onSuccessfulResponseResults: ((results: SearchResultsT, page: Int?) -> Unit)? = null

    override fun onResponse(call: Call<ApiResponseT>, response: Response<ApiResponseT>) {
        super.onResponse(call, response)
        val responseSuccessful = wasResponseSuccessful(response)
        if (responseSuccessful) {
            val apiResponse: ApiResponseT = response.body()!!
            apiResponse.page = getPageFrom(call.request())
            with(apiResponse) { onSuccessfulResponseResults?.invoke(results, page) }
        }
    }

    private fun getPageFrom(request: Request): Int? {
        val url = request.url()
        return url.queryParameter("page")?.toIntOrNull()
    }

}