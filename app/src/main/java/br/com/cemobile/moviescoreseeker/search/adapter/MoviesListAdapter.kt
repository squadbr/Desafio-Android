package br.com.cemobile.moviescoreseeker.search.adapter

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.cemobile.moviescoreseeker.R
import br.com.cemobile.moviescoreseeker.base.DataBoundListAdapter
import br.com.cemobile.moviescoreseeker.core.Executors
import br.com.cemobile.moviescoreseeker.databinding.MoviesListItemBinding
import br.com.cemobile.moviescoreseeker.model.Movie
import br.com.cemobile.moviescoreseeker.util.loadImage

class MoviesListAdapter(
        private val dataBindingComponent: DataBindingComponent,
        executors: Executors,
        private val listener: ((Movie) -> Unit)?
) : DataBoundListAdapter<Movie, MoviesListItemBinding>(
        executors = executors,
        diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.imdbId == newItem.imdbId

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.title == newItem.title && oldItem.poster == newItem.poster
        }
) {

    override fun createBinding(parent: ViewGroup): MoviesListItemBinding {
        val binding = DataBindingUtil.inflate<MoviesListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.movies_list_item,
                parent,
                false,
                dataBindingComponent
        )

        binding.root.setOnClickListener { _ ->
            binding.movie?.let {
                listener?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: MoviesListItemBinding, item: Movie) {
        binding.movie = item
        binding.posterImage.loadImage(item.poster)
        binding.executePendingBindings()
    }

}