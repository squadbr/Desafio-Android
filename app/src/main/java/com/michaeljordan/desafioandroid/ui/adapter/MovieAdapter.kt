package com.michaeljordan.desafioandroid.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.michaeljordan.desafioandroid.R
import com.michaeljordan.desafioandroid.databinding.MovieListItemBinding
import com.michaeljordan.desafioandroid.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(val context: Context, val listener: MovieAdapterOnClickListener) :
        RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var items: List<Movie?> = ArrayList()
    private var itemsFiltered: List<Movie?> = ArrayList()

    interface MovieAdapterOnClickListener {
        fun onClick(movie: Movie?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieListItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(items[position])

    fun setData(movies: List<Movie?>) {
        items = movies
        itemsFiltered = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root),
            View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: Movie?) {
            binding.tvTitle.text = item?.title
            binding.tvRating.text = item?.imdbRating

            Picasso.with(context)
                    .load(item?.poster)
                    .error(R.drawable.ic_no_poster)
                    .into(binding.ivPoster)
        }

        override fun onClick(v: View?) {
            listener.onClick(items[adapterPosition])
        }

    }

}