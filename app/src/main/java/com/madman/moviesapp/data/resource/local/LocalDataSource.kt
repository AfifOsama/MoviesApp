package com.madman.moviesapp.data.resource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.local.room.MoviesAppDao

class LocalDataSource private constructor(private val mMoviesAppDao: MoviesAppDao) {
    fun getMovies(): DataSource.Factory<Int, MoviesEntity> = mMoviesAppDao.getMovies()

    fun getFavoriteMovies(): DataSource.Factory<Int, MoviesEntity> =
        mMoviesAppDao.getFavoriteMovies()

    fun getDetailMovie(movieId: Int): LiveData<MoviesEntity> = mMoviesAppDao.getDetailMovie(movieId)

    fun insertListMovies(movies: List<MoviesEntity>) = mMoviesAppDao.insertListMovies(movies)

    fun setFavoriteMovie(movie: MoviesEntity) {
        movie.isFavorite = !movie.isFavorite
        mMoviesAppDao.updateMovie(movie)
    }

    fun getTvShows(): DataSource.Factory<Int, TVShowEntity> = mMoviesAppDao.getTvShows()

    fun getFavoriteTvShows(): DataSource.Factory<Int, TVShowEntity> =
        mMoviesAppDao.getFavoriteTvShows()

    fun getDetailTvShow(tvShowId: Int): LiveData<TVShowEntity> =
        mMoviesAppDao.getDetailTvShow(tvShowId)

    fun insertListTvShows(tvShows: List<TVShowEntity>) = mMoviesAppDao.insertListTvShows(tvShows)

    fun setFavoriteTvShow(tvShow: TVShowEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        mMoviesAppDao.updateTvShow(tvShow)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(moviesAppDao: MoviesAppDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(moviesAppDao)
    }
}