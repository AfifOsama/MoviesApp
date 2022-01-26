package com.madman.moviesapp.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.utils.DataDummy

class TVShowViewModel(private val moviesAppRepository: MoviesAppRepository):ViewModel() {
    fun getTVShow(): LiveData<List<TVShowEntity>> = moviesAppRepository.getTvShow()
}