package com.madman.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.utils.DataDummy
import com.madman.moviesapp.vo.Resource

class MoviesViewModel(private val moviesAppRepository: MoviesAppRepository): ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> = moviesAppRepository.getMovies()
}