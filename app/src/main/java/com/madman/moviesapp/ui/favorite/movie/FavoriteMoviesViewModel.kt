package com.madman.moviesapp.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository

class FavoriteMoviesViewModel(private val moviesAppRepository: MoviesAppRepository):ViewModel() {
    fun getListFavoriteMovies(): LiveData<PagedList<MoviesEntity>> = moviesAppRepository.getListFavoriteMovies()
}