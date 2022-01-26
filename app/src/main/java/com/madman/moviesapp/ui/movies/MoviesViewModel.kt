package com.madman.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.utils.DataDummy

class MoviesViewModel(private val moviesAppRepository: MoviesAppRepository): ViewModel() {
    fun getMovies(): LiveData<List<MoviesEntity>> = moviesAppRepository.getMovies()
}