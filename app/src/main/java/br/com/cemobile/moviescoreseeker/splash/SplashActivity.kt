package br.com.cemobile.moviescoreseeker.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.cemobile.moviescoreseeker.Constants
import br.com.cemobile.moviescoreseeker.R
import br.com.cemobile.moviescoreseeker.onboarding.OnboardingActivity
import br.com.cemobile.moviescoreseeker.search.SearchMoviesActivity
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class SplashActivity : AppCompatActivity() {

    private companion object {
        const val DEFAULT_TIME = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        GlobalScope.launch {
            delay(DEFAULT_TIME)
            navigateToNextScreen()
        }

    }

    private fun isFirstTime(): Boolean {
        val preferences = getSharedPreferences(
                getString(R.string.preferences_file), Context.MODE_PRIVATE)
        return preferences.getBoolean(Constants.KEY_SHOW_ONBOARDING, true)
    }

    private fun navigateToNextScreen() {
        if (isFirstTime()) {
            navigateToOnboarding()
        } else {
            navigateToSearchMovies()
        }
        finish()
    }

    private fun navigateToOnboarding() {
        startActivity(Intent(this, OnboardingActivity::class.java))
    }

    private fun navigateToSearchMovies() {
        startActivity(Intent(this, SearchMoviesActivity::class.java))
    }

}