package com.madman.moviesapp.ui.detail.movies

import androidx.lifecycle.ViewModel
import com.madman.moviesapp.data.MoviesEntity
import com.madman.moviesapp.utils.DataDummy

class DetailMovieViewModel: ViewModel() {
    private lateinit var movieId: String

    fun selectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): MoviesEntity {
        lateinit var movie: MoviesEntity
        val movieEntities = DataDummy.generateDummyMovies()
        for (movieEntity in movieEntities) {
            if (movieEntity.title == movieId) {
                movie = movieEntity
            }
        }
        return movie
    }
}