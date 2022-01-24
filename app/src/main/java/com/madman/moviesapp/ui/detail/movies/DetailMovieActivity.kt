package com.madman.moviesapp.ui.detail.movies

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.madman.moviesapp.R
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.databinding.ActivityDetailMovieBinding
import com.madman.moviesapp.databinding.ContentDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var contentBinding: ContentDetailMovieBinding
    private val movieViewModel: DetailMovieViewModel by viewModels()
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
        val extras = intent.extras
        if (extras != null) {
            val movieName = extras.getString(EXTRA_DETAIL)
            if (movieName != null) {
                movieViewModel.selectedMovie(movieName)
                populateMovies(movieViewModel.getMovie())
            }
        }
        contentBinding.tbFavorite.setOnClickListener {
            if (contentBinding.tbFavorite.isChecked) {
                Toast.makeText(
                    this@DetailMovieActivity,
                    "You've favorite this movie",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun populateMovies(movie: MoviesEntity) {
        with(contentBinding) {
            tvTitle.text = movie.title
            tvDescription.text = movie.description
            tvDirector.text = movie.director
            tvGenre.text = movie.genre
            tvRating.text = movie.rating
            tvScore.text = movie.score
            tvReleaseDate.text = movie.releaseDate
            Glide.with(this@DetailMovieActivity)
                .load(movie.imgPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(imgMovie)
            Glide.with(this@DetailMovieActivity)
                .load(movie.imgPath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(imgPoster)
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}