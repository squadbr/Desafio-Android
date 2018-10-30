package com.gabrielfeo.openmoviedbsearch.view

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
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

    fun onBackPressed(onBackStackEmpty: () -> Unit) = fragmentManager?.let {
        if (it.backStackEntryCount > 0) it.popBackStack()
        else onBackStackEmpty()
    }

    /**
     * Extension function for better readability in transaction methods. Also, enforces that every
     * transaction is added to the back stack.
     */
    private fun FragmentManager.transaction(
        transactionName: String,
        transactionFunction: FragmentTransaction.() -> Unit
    ) {
        this.beginTransaction()
            .addToBackStack(transactionName)
            .apply(transactionFunction)
            .commit()
    }

}