package com.gabrielfeo.openmoviedbsearch.view

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import com.gabrielfeo.openmoviedbsearch.R
import com.gabrielfeo.openmoviedbsearch.view.fragment.MovieSearchFragment

/**
 * Handles all app navigation through methods called from [MainActivity] and fragments. Methods are
 * analogous to lifecycle callbacks e.g. [onMainActivityCreated]. All classes are coupled to `Navigator` so
 * that the classes can be decoupled from each other.
 */
class Navigator(private var fragmentManager: FragmentManager?) : LifecycleObserver {

    private val containerId = R.id.main_fl_container

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cleanUp() {
        fragmentManager = null
    }

    fun onMainActivityCreated() = fragmentManager?.transaction("activityCreated") {
        setTransition(TRANSIT_FRAGMENT_OPEN)
        add(containerId, MovieSearchFragment.newInstance())
    }

    /**
     * Extension function for better readability in transaction methods. Also, enforces that every
     * transaction is added to the back stack.
     */
    private fun FragmentManager.transaction(
        transactionName: String,
        transactionFunction: FragmentTransaction.() -> Unit
    ) {
        val transaction = this.beginTransaction()
            .addToBackStack(transactionName)
            .apply(transactionFunction)
            .commit()
    }

}