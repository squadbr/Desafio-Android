package br.com.cemobile.moviescoreseeker.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.cemobile.moviescoreseeker.Constants.EXTRA_MOVIE
import br.com.cemobile.moviescoreseeker.R
import br.com.cemobile.moviescoreseeker.model.Movie
import kotlinx.android.synthetic.main.app_bar.*

class MovieDetailsActivity : AppCompatActivity() {

    private val movie: Movie by lazy {
        intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
    }

    companion object {
        fun launchIntent(from: Context, movie: Movie) =
                Intent(from, MovieDetailsActivity::class.java).apply {
                    putExtra(EXTRA_MOVIE, movie)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details_activity)
        setupActionBar(movie.title)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.contentLayout, MovieDetailsFragment.newInstance(movie), "details")
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


    private fun setupActionBar(title: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = title
            it.setDisplayHomeAsUpEnabled(true)
        }
    }
}