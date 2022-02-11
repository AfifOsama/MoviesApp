package com.madman.moviesapp.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository

class DetailTvshowViewModel(private val moviesAppRepository: MoviesAppRepository) : ViewModel() {
    fun getTvShow(id: Int): LiveData<TVShowEntity> {
        return moviesAppRepository.getTvShowDetail(id)
    }

    fun setFavoriteTvShow(tvShow: TVShowEntity) {
        moviesAppRepository.setFavoriteTvShow(tvShow)
    }
}