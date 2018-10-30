package com.gabrielfeo.openmoviedbsearch.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gabrielfeo.openmoviedbsearch.R
import com.oshi.libsearchtoolbar.SearchAnimationToolbar

class MainActivity : AppCompatActivity() {

    val navigator = Navigator(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(navigator)
        findToolbar()
        navigator.onMainActivityCreated()
    }

    private fun findToolbar() {
        val animatedToolbar = findViewById<SearchAnimationToolbar>(R.id.main_t_toolbar)
        animatedToolbar.setSupportActionBar(this) /* Lint reports this as an issue, but the Jetifier tool
        solves this at compile time. See gradle.properties */
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigator.onBackPressed(onBackStackEmpty = { finish() })
    }
}
