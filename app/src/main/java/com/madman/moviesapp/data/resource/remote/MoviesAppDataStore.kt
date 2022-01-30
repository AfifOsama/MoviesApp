package com.madman.moviesapp.data.resource.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.vo.Resource

interface MoviesAppDataStore {
    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>>

    fun getMovieDetail(movieId: Int): LiveData<MoviesEntity>

    fun getTvShow(): LiveData<Resource<PagedList<TVShowEntity>>>

    fun getTvShowDetail(tvShowId: Int): LiveData<TVShowEntity>

    fun setFavoriteMovie(movie: MoviesEntity)

    fun getListFavoriteMovies(): LiveData<PagedList<MoviesEntity>>

    fun setFavoriteTvShow(tvShow: TVShowEntity)

    fun getListFavoriteTvShow(): LiveData<PagedList<TVShowEntity>>

}