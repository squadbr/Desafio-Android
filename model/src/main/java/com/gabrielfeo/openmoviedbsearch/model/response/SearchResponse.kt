package com.gabrielfeo.openmoviedbsearch.model.response

interface SearchResponse<out ResultsT> {

    val results: ResultsT
    var page: Int?

}