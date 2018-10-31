package com.gabrielfeo.openmoviedbsearch.view

import androidx.fragment.app.Fragment

/**
 * Since all the fragments used in the app have [MainActivity] in common as the container, this provides
 * access to the `MainActivity` methods with no casting needed.
 */
internal val Fragment.mainActivity
    get() = activity as MainActivity

/**
 * Since all navigation is handled by the [MainActivity.navigator], this provides concise access to the
 * [Navigator].
 */
internal val Fragment.navigator
    get() = mainActivity.navigator