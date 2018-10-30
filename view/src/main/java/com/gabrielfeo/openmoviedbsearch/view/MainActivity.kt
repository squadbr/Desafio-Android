package com.gabrielfeo.openmoviedbsearch.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.gabrielfeo.openmoviedbsearch.R

class MainActivity : AppCompatActivity() {

    val navigator = Navigator(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(navigator)
        findActionBar()
        navigator.onMainActivityCreated()
    }

    private fun findActionBar() {
        val toolbar: Toolbar = findViewById(R.id.main_t_toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigator.onBackPressed(onBackStackEmpty = { finish() })
    }
}
