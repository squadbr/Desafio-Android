package br.com.cemobile.moviescoreseeker.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cemobile.moviescoreseeker.Constants.EXTRA_MOVIE
import br.com.cemobile.moviescoreseeker.R
import br.com.cemobile.moviescoreseeker.databinding.MovieDetailsFragmentBinding
import br.com.cemobile.moviescoreseeker.model.Movie
import br.com.cemobile.moviescoreseeker.util.ToMovieDetails
import br.com.cemobile.moviescoreseeker.util.autoCleared
import org.koin.android.ext.android.inject

class MovieDetailsFragment : Fragment() {

    private val factory: MovieDetailsViewModelFactory by inject()
    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(MovieDetailsViewModel::class.java)
    }
    private val movie: Movie by lazy { arguments!!.getParcelable<Movie>(EXTRA_MOVIE) }
    private var binding by autoCleared<MovieDetailsFragmentBinding>()

    companion object {
        fun newInstance(movie: Movie): MovieDetailsFragment {
            val args = Bundle().apply {
                putParcelable(EXTRA_MOVIE, movie)
            }

            return MovieDetailsFragment().apply { arguments = args}
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_fragment, container, false)
        binding.movie = ToMovieDetails(movie)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bind()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMovieDetails(movie.imdbId)
    }

    private fun bind() {
        viewModel.movieDetailsLiveData.observe(this, Observer { resource ->
            binding.resource = resource
            resource?.data?.let {
                binding.movie = it
            }
        })
    }

}