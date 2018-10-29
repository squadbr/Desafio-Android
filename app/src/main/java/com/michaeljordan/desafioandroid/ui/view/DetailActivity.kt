package com.michaeljordan.desafioandroid.ui.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.michaeljordan.desafioandroid.R
import com.michaeljordan.desafioandroid.databinding.ActivityDetailBinding
import com.michaeljordan.desafioandroid.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.toolbar.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        val movie = intent.getParcelableExtra<Parcelable>("movie") as Movie

        setToolbar(movie.title)
        binding.tvTitleDetail.text = movie.title
        binding.tvRatingDetail.text = movie.imdbRating
        binding.tvPlotDetail.text = movie.plot

        Picasso.with(baseContext)
                .load(movie.poster)
                .error(R.drawable.ic_no_poster)
                .into(binding.ivPosterDetail)

        if (!movie.poster.isEmpty() && movie.poster != "N/A") {
            binding.ivPosterDetail.setOnClickListener {
                val intent = Intent(this, FullScreenImageActivity::class.java)
                intent.putExtra("image_url", movie.poster)
                intent.putExtra("title", movie.title)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setToolbar(movieTitle: String) {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = movieTitle
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}