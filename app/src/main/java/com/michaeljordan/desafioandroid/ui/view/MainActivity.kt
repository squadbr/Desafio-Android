package com.michaeljordan.desafioandroid.ui.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import com.michaeljordan.desafioandroid.R
import com.michaeljordan.desafioandroid.databinding.ActivityMainBinding
import com.michaeljordan.desafioandroid.model.Movie
import com.michaeljordan.desafioandroid.ui.adapter.MovieAdapter
import com.michaeljordan.desafioandroid.ui.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity(), MovieAdapter.MovieAdapterOnClickListener {
    private var viewModel : MovieViewModel? = null
    private lateinit var binding: ActivityMainBinding
    private var movies: ArrayList<Movie?> = ArrayList()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = getString(R.string.app_name)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter(applicationContext, this)
        binding.rvMovies.adapter = adapter
        subscribe()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.search)

        val searchView = searchItem.actionView as SearchView
        searchView.isFocusable = false
        searchView.queryHint = getText(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel!!.searchMovies(query)
                binding.pbSearch.visibility = View.VISIBLE
                binding.tvMsgSearch.visibility = View.GONE
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }
        })
        return true
    }

    private fun subscribe() {
        viewModel!!.getSearchMoviesObservable().observe(this, Observer<List<Movie?>> { results ->
            binding.pbSearch.visibility = View.GONE
            movies = results as ArrayList<Movie?>
            if (movies.size > 0) {
                movies = ArrayList(movies.sortedBy { it?.title })
                adapter.setData(movies)
                binding.rvMovies.visibility = View.VISIBLE
                binding.tvMsgSearch.visibility = View.GONE
            } else {
                binding.rvMovies.visibility = View.GONE
                binding.tvMsgSearch.visibility = View.VISIBLE
            }
        })
    }

    override fun onClick(movie: Movie?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

}
