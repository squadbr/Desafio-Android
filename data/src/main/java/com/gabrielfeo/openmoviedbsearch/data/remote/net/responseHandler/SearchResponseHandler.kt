package com.gabrielfeo.openmoviedbsearch.data.remote.net.responseHandler

import com.gabrielfeo.openmoviedbsearch.model.response.SearchResponse
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response

/**
 * Handles a common API JSON response pattern for a search method, providing configurable function
 * properties for error handling and success handling, as well as the handling of actual results. The
 * pattern for search method JSON responses is that a `results` array will have an accompanying `page` key,
 * for example, along with any number of metadata keys. Hence, `SearchResponseHandler` provides properties
 * for taking action on the actual results, and not only the response POJO that contains them.
 *
 * @param ApiResponseT the API response POJO type, i.e. the actual type from the [Call]. Specifically, this
 * type must implement the [SearchResponse] interface.
 * @param SearchResultsT the type of search result contained in the [SearchResponse.results] property
 */
class SearchResponseHandler<ApiResponseT, SearchResultsT> : ApiResponseHandler<ApiResponseT>()
        where ApiResponseT : SearchResponse<SearchResultsT> {

    /**
     * Function to be called in case of successful response results. Nullable so that it's only invoked if
     * set.
     * @see onResponse
     * @see wasResponseSuccessful
     */
    var onSuccessfulResponseResults: ((results: SearchResultsT?, page: Int?) -> Unit)? = null

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