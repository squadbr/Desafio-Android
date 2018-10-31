package com.gabrielfeo.openmoviedbsearch.model.response

/**
 * An abstraction of a common API response pattern for search methods, in which the search [results] come
 * along with a [page] key.
 * @param ResultsT the type of the search [results]
 */
interface SearchResponse<out ResultsT> {

    val results: ResultsT
    var page: Int?

}