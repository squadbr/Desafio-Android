package br.com.cemobile.moviescoreseeker.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cemobile.moviescoreseeker.R
import br.com.cemobile.moviescoreseeker.core.Executors
import br.com.cemobile.moviescoreseeker.core.FragmentDataBindingComponent
import br.com.cemobile.moviescoreseeker.core.GridSpacingItemDecoration
import br.com.cemobile.moviescoreseeker.databinding.SearchMoviesFragmentBinding
import br.com.cemobile.moviescoreseeker.details.MovieDetailsActivity
import br.com.cemobile.moviescoreseeker.model.Movie
import br.com.cemobile.moviescoreseeker.model.Resource
import br.com.cemobile.moviescoreseeker.search.adapter.MoviesListAdapter
import br.com.cemobile.moviescoreseeker.util.autoCleared
import org.koin.android.ext.android.inject

class SearchMoviesFragment : Fragment() {

    private val appExecutors: Executors by inject()
    private val factory: SearchMoviesViewModelFactory by inject()
    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(SearchMoviesViewModel::class.java)
    }

    // mutable for testing
    private var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private var queryViewModel by autoCleared<QueryViewModel>()
    private var adapter by autoCleared<MoviesListAdapter>()
    var binding by autoCleared<SearchMoviesFragmentBinding>()

    companion object {
        private const val MAX_MOVIES_PER_LINE_IN_PORTRAIT = 2
        private const val MAX_MOVIES_PER_LINE_IN_LANDSCAPE = 3

        fun newInstance() = SearchMoviesFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_movies_fragment, container, false)
        queryViewModel = QueryViewModel(viewModel.query)
        binding.query = queryViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMoviesList()
        bind()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            (binding.moviesList.layoutManager as GridLayoutManager).spanCount =
                    MAX_MOVIES_PER_LINE_IN_LANDSCAPE
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            (binding.moviesList.layoutManager as GridLayoutManager).spanCount =
                    MAX_MOVIES_PER_LINE_IN_PORTRAIT
        }
    }

    private fun setupMoviesList() {
        var maxMoviesPerLine = MAX_MOVIES_PER_LINE_IN_LANDSCAPE
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            maxMoviesPerLine = MAX_MOVIES_PER_LINE_IN_PORTRAIT
        }

        this.adapter = MoviesListAdapter(dataBindingComponent, appExecutors) { movie ->
            navigateToDetails(movie)
        }

        binding.moviesList.setHasFixedSize(true)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.sixteen_dips)
        binding.moviesList.layoutManager = GridLayoutManager(context!!, maxMoviesPerLine)
        binding.moviesList.addItemDecoration(GridSpacingItemDecoration(maxMoviesPerLine, spacingInPixels, true, 0))
        binding.moviesList.adapter = adapter
    }

    private fun bind() {
        viewModel.loadingLiveData.observe(this, Observer { show ->
            binding.resource = Resource.loading(show)
        })

        viewModel.errorLiveData.observe(this, Observer { error ->
            error?.let { binding.resource = Resource.error(error, null) }
        })

        viewModel.moviesLiveData.observe(this, Observer { resources ->
            binding.resource = resources
            adapter.submitList(if (resources?.data != null) resources.data else emptyList())
        })

        queryViewModel.mutableQuery.observe(this, Observer<String> { query ->
            viewModel.query = query!!
        })
    }

    private fun navigateToDetails(movie: Movie) {
        context?.let {
            val intent = MovieDetailsActivity.launchIntent(it, movie)
            startActivity(intent)
        }
    }

}