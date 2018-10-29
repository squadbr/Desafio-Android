package com.gabrielfeo.openmoviedbsearch.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gabrielfeo.openmoviedbsearch.R

class MainActivity : AppCompatActivity() {

    val navigator = Navigator(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(navigator)
        navigator.onMainActivityCreated()
    }
}
