package com.gabrielfeo.openmoviedbsearch.data.remote.net.responseHandler

import com.gabrielfeo.openmoviedbsearch.model.response.SearchResponse
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response

class SearchResponseHandler<ApiResponseT, SearchResultsT> : ApiResponseHandler<ApiResponseT>()
        where ApiResponseT : SearchResponse<SearchResultsT> {

    var onResultsReceived: ((results: SearchResultsT, page: Int?) -> Unit)? = null

    override fun onResponse(call: Call<ApiResponseT>, response: Response<ApiResponseT>) {
        super.onResponse(call, response)
        if (responseSuccessful) {
            val apiResponse: ApiResponseT = response.body()!!
            val page: Int? = getPageFrom(call.request())
            apiResponse.page = page
            with(apiResponse) { onResultsReceived?.invoke(results, page) }
        }
    }

    private fun getPageFrom(request: Request): Int? {
        val url = request.url()
        return url.queryParameter("page")?.toIntOrNull()
    }

}