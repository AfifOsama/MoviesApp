package com.madman.moviesapp.data.resource.remote

import androidx.lifecycle.LiveData
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity

interface MoviesAppDataStore {
    fun getMovies(): LiveData<List<MoviesEntity>>

    fun getMovieDetail(movieId: Int): LiveData<MoviesEntity>

    fun getTvShow(): LiveData<List<TVShowEntity>>

    fun getTvShowDetail(tvShowId: Int): LiveData<TVShowEntity>
}