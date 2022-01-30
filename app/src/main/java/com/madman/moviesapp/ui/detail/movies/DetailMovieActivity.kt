package com.madman.moviesapp.ui.detail.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.databinding.ActivityDetailMovieBinding
import com.madman.moviesapp.databinding.ContentDetailMovieBinding
import com.madman.moviesapp.utils.GlideHelper
import com.madman.moviesapp.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var contentBinding: ContentDetailMovieBinding
    private lateinit var movieViewModel: DetailMovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val detailBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentBinding = detailBinding.detailContent
        setContentView(detailBinding.root)

        setSupportActionBar(detailBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initiateUI()
    }

    private fun initiateUI() {
        val factory = ViewModelFactory.getInstance(this)
        movieViewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
        val extrasId = intent.getIntExtra(EXTRA_DETAIL, 0)
        contentBinding.progressBar.visibility = View.VISIBLE
        movieViewModel.getMovie(extrasId).observe(this, {
            contentBinding.progressBar.visibility = View.GONE
            populateMovies(it)
        })
    }

    private fun populateMovies(movie: MoviesEntity) {
        val favStatus = movie.isFavorite
        with(contentBinding) {
            tvTitle.text = movie.title
            tvDescription.text = movie.description
            tvScore.text = movie.score.toString()
            tvReleaseDate.text = movie.releaseDate
            GlideHelper.glideImage(
                this@DetailMovieActivity,
                GlideHelper.API_IMG_ENDPOINT + GlideHelper.ENDPOINT_IMG_SIZE_W185 + movie.imgPosterPath,
                imgPoster
            )
            GlideHelper.glideImage(
                this@DetailMovieActivity,
                GlideHelper.API_IMG_ENDPOINT + GlideHelper.ENDPOINT_IMG_SIZE_W780 + movie.imgPreviewPath,
                imgMovie
            )
            tbFavorite.setOnClickListener {
                setFavoriteMovie(movie)
            }
            contentBinding.tbFavorite.isChecked = favStatus
        }
    }

    private fun setFavoriteMovie(movie: MoviesEntity?) {
        if (movie != null) {
            if (movie.isFavorite) {
                Toast.makeText(
                    this@DetailMovieActivity,
                    "You've unfavorite this movie",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@DetailMovieActivity,
                    "You've favorite this movie",
                    Toast.LENGTH_SHORT
                ).show()
            }
            movieViewModel.setFavoriteMovie(movie)
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}