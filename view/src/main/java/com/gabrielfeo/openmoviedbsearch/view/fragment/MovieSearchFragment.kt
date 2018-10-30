package com.gabrielfeo.openmoviedbsearch.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabrielfeo.openmoviedbsearch.R
import com.gabrielfeo.openmoviedbsearch.data.MovieRepository
import com.gabrielfeo.openmoviedbsearch.view.adapter.MovieAdapter

class MovieSearchFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView;
    private var adapter = MovieAdapter()

    companion object {
        fun newInstance() = MovieSearchFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_moviesearch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViewsOn(view)
        setupRecyclerView()
        // Test for RecyclerView:
        MovieRepository().search("godfather") { results, _ ->
            adapter.movies = results
        }
    }

    private fun findViewsOn(view: View) {
        recyclerView = view.findViewById(R.id.moviesearch_rv_results_list)
    }

    private fun setupRecyclerView() = recyclerView.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = this@MovieSearchFragment.adapter
        setHasFixedSize(true)
    }


}