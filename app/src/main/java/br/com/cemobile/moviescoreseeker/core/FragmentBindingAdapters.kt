package br.com.cemobile.moviescoreseeker.core

import android.databinding.BindingAdapter
import android.support.v4.app.Fragment
import android.widget.ImageView

/**
 * Binding adapters that work with a fragment instance.
 */
class FragmentBindingAdapters(val fragment: Fragment) {

    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {
        GlideApp.with(fragment).load(url).into(imageView)
    }

}
