package com.madman.moviesapp.ui.detail.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.utils.DataDummy

class DetailMovieViewModel(private val moviesAppRepository: MoviesAppRepository): ViewModel() {
    fun getMovie(id:Int): LiveData<MoviesEntity>{
        return moviesAppRepository.getMovieDetail(id)
    }
}