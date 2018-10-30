package com.gabrielfeo.openmoviedbsearch.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gabrielfeo.openmoviedbsearch.R
import com.gabrielfeo.openmoviedbsearch.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movies: List<Movie>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies?.size ?: 0

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        val currentMovie = movies?.get(position) ?: return
        setMovieInfo(viewHolder, currentMovie)
    }

    private fun setMovieInfo(viewHolder: MovieViewHolder, movie: Movie) = with(viewHolder) {
        setPosterImageOn(viewHolder, movie)
        titleView.text = movie.title
        directorView.text = movie.director
        yearView.text = movie.year
        ratingView.text = movie.rating
    }

    private fun setPosterImageOn(viewHolder: MovieViewHolder, movie: Movie) =
        Picasso.get().load(movie.posterUrl).into(viewHolder.posterView)

    class MovieViewHolder(movieView: View) : ViewHolder(movieView) {

        val titleView: TextView = itemView.findViewById(R.id.item_movie_tv_title)
        val posterView: ImageView = itemView.findViewById(R.id.item_movie_iv_poster)
        val directorView: TextView = itemView.findViewById(R.id.item_movie_tv_director)
        val yearView: TextView = itemView.findViewById(R.id.item_movie_tv_year)
        val ratingView: TextView = itemView.findViewById(R.id.item_movie_tv_rating)

    }

}