package com.madman.moviesapp.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository

class FavoriteTvShowsViewModel(private val moviesAppRepository: MoviesAppRepository) : ViewModel() {
    fun getListFavoriteTvShows(): LiveData<PagedList<TVShowEntity>> =
        moviesAppRepository.getListFavoriteTvShow()
}