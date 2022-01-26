package com.madman.moviesapp.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository

class DetailTvshowViewModel(private val moviesAppRepository: MoviesAppRepository) : ViewModel() {
//    private lateinit var tvShowName: String
//
//    fun selectedTVshow(TVshowId: String) {
//        this.tvShowName = TVshowId
//    }

    fun getTVshow(id: Int): LiveData<TVShowEntity> {
        return moviesAppRepository.getTvShowDetail(id)
    }
}