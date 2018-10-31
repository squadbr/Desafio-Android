package com.gabrielfeo.openmoviedbsearch.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gabrielfeo.openmoviedbsearch.R
import com.gabrielfeo.openmoviedbsearch.view.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso


class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var titleView: TextView
    private lateinit var directorView: TextView
    private lateinit var posterView: ImageView
    private lateinit var countriesView: TextView
    private lateinit var yearView: TextView
    private lateinit var languagesView: TextView
    private lateinit var runtimeView: TextView
    private lateinit var ratingTitleView: TextView
    private lateinit var ratingView: TextView
    private lateinit var synopsisView: TextView

    companion object {

        private const val ARG_KEY_MOVIE = "movie"

        fun newInstance(serializedMovie: String) = MovieDetailFragment().apply {
            val bundle = Bundle()
            bundle.putString(ARG_KEY_MOVIE, serializedMovie)
            arguments = bundle
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel()
        setMovieFromArguments()
    }

    private fun setMovieFromArguments() = arguments?.getString(ARG_KEY_MOVIE)?.let { viewModel.setMovie(it) }

    private fun getViewModel() {
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_moviedetail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViewsOn(view)
        setInfoOnViews()
    }

    private fun findViewsOn(view: View) {
        titleView = view.findViewById(R.id.moviedetail_tv_title)
        directorView = view.findViewById(R.id.moviedetail_tv_director)
        posterView = view.findViewById(R.id.moviedetail_iv_poster)
        countriesView = view.findViewById(R.id.moviedetail_tv_countries)
        yearView = view.findViewById(R.id.moviedetail_tv_year)
        languagesView = view.findViewById(R.id.moviedetail_tv_languages)
        runtimeView = view.findViewById(R.id.moviedetail_tv_runtime)
        ratingTitleView = view.findViewById(R.id.moviedetail_tv_rating_title)
        ratingView = view.findViewById(R.id.moviedetail_tv_rating_value)
        synopsisView = view.findViewById(R.id.moviedetail_tv_synopsis)
    }

    private fun setInfoOnViews() = viewModel.movie?.let {
        titleView.text = it.title
        directorView.text = it.director
        countriesView.text = it.countries
        yearView.text = it.year
        languagesView.text = it.languages
        runtimeView.text = it.runtime
        ratingTitleView.text = getString(R.string.moviedetail_rating_title)
        ratingView.text = it.rating
        synopsisView.text = it.synopsis
        setPosterImage(it.posterUrl)
    }

    private fun setPosterImage(posterUrl: String) = Picasso.get().load(posterUrl).into(posterView)

}