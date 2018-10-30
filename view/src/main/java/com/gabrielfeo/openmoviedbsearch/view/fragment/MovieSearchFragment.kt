package com.gabrielfeo.openmoviedbsearch.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielfeo.openmoviedbsearch.R
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