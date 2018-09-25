package br.com.cemobile.moviescoreseeker.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.cemobile.moviescoreseeker.Constants
import br.com.cemobile.moviescoreseeker.R
import br.com.cemobile.moviescoreseeker.search.SearchMoviesActivity
import kotlinx.android.synthetic.main.onboarding_activity.*

class OnboardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_activity)
        noShowMoreOnboarding()
        setupViews()
    }

    private fun setupViews() {
        letsGoButton.setOnClickListener {
            navigateToSearchMovies()
            finish()
        }
    }

    private fun navigateToSearchMovies() {
        startActivity(Intent(this, SearchMoviesActivity::class.java))
    }

    private fun noShowMoreOnboarding() {
        val preferences = getSharedPreferences(
                getString(R.string.preferences_file), Context.MODE_PRIVATE)
        preferences.edit()
                .putBoolean(Constants.KEY_SHOW_ONBOARDING, false)
                .apply()
    }
}
