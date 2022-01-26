package com.madman.moviesapp.data.resource

import com.madman.moviesapp.data.resource.remote.api.ApiConfig
import com.madman.moviesapp.data.resource.remote.response.MovieResponse
import com.madman.moviesapp.data.resource.remote.response.TVShowResponse
import com.madman.moviesapp.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {
    suspend fun getMovies(
        callback: LoadMoviesCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getMovies().await().result?.let {
            callback.onAllMoviesReceived(
                it
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    suspend fun getMovieDetail(movieId: Int, callback: LoadMovieDetailCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getDetailMovie(movieId).await().let {
            callback.onMovieDetailReceived(
                it
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadMovieDetailCallback {
        fun onMovieDetailReceived(movieResponse: MovieResponse)
    }

    suspend fun getTvShow(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getTvShow().await().result?.let {
            callback.onAllTvShowsReceived(
                it
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadTvShowCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TVShowResponse>)
    }

    suspend fun getTvShowDetail(tvShowId: Int, callback: LoadTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getDetailTvShow(tvShowId).await().let {
            callback.onTvShowDetailReceived(
                it
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadTvShowDetailCallback {
        fun onTvShowDetailReceived(tvShowResponse: TVShowResponse)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}