package br.com.cemobile.moviescoreseeker.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.cemobile.moviescoreseeker.R
import kotlinx.android.synthetic.main.app_bar.*

class SearchMoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_movies_activity)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.contentLayout, SearchMoviesFragment.newInstance(), "search")
                    .commit()
        }
    }

}
