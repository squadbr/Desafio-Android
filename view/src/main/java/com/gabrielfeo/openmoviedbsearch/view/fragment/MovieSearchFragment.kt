package com.gabrielfeo.openmoviedbsearch.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielfeo.openmoviedbsearch.R
import com.gabrielfeo.openmoviedbsearch.view.adapter.MovieAdapter
import com.gabrielfeo.openmoviedbsearch.view.viewmodel.MovieSearchViewModel

class MovieSearchFragment : Fragment() {

    private lateinit var viewModel: MovieSearchViewModel
    private lateinit var recyclerView: RecyclerView;
    private var adapter = MovieAdapter()

    companion object {
        fun newInstance() = MovieSearchFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel()
    }

    private fun getViewModel() {
        viewModel = ViewModelProviders.of(this).get(MovieSearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_moviesearch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findRecyclerViewOn(view)
        setupRecyclerView()
        observeMoviesList()
    }

    private fun findRecyclerViewOn(view: View) {
        recyclerView = view.findViewById(R.id.moviesearch_rv_results_list)
    }

    private fun setupRecyclerView() = recyclerView.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = this@MovieSearchFragment.adapter
        setHasFixedSize(true)
    }

    private fun observeMoviesList() {
        viewModel.moviesList.observe(this, Observer { list -> adapter.movies = list })
        viewModel.searchMovies("scarface") // TODO Remove test for RecyclerView
    }

}