package com.gabrielfeo.openmoviedbsearch.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielfeo.openmoviedbsearch.R
import com.gabrielfeo.openmoviedbsearch.view.adapter.MovieAdapter
import com.gabrielfeo.openmoviedbsearch.view.mainActivity
import com.gabrielfeo.openmoviedbsearch.view.viewmodel.MovieSearchViewModel
import com.oshi.libsearchtoolbar.SearchAnimationToolbar


class MovieSearchFragment : Fragment() {

    private lateinit var viewModel: MovieSearchViewModel
    private lateinit var toolbar: SearchAnimationToolbar
    private lateinit var recyclerView: RecyclerView;
    private var adapter = MovieAdapter()

    companion object {
        fun newInstance() = MovieSearchFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar = mainActivity.findViewById(R.id.main_t_toolbar)
        setupSearchBar()
    }

    private fun setupSearchBar() = toolbar.setOnSearchQueryChangedListener(getSearchBarListener())

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.let { inflater?.inflate(R.menu.menu_moviesearch, menu) }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.moviesearch_menu_search -> true.also { expandSearch() }
        else -> super.onOptionsItemSelected(item)
    }

    private fun expandSearch() = toolbar.onSearchIconClick()

    private fun getSearchBarListener() = object : SearchAnimationToolbar.OnSearchQueryChangedListener {

        override fun onSearchCollapsed() = toolbar.setTitle(getString(R.string.app_name))

        override fun onSearchSubmitted(query: String?) = viewModel.searchMovies(query ?: "")
            .also { hideKeyboard() }

        override fun onSearchQueryChanged(query: String?) {}

        override fun onSearchExpanded() {}

    }

    fun hideKeyboard() = view?.let {
        val inputMethodManager = it.context.getSystemService(Activity.INPUT_METHOD_SERVICE)
                as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
    }

}