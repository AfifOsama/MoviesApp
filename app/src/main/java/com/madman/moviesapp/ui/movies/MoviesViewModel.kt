package com.madman.moviesapp.ui.movies

import androidx.lifecycle.ViewModel
import com.madman.moviesapp.data.MoviesEntity
import com.madman.moviesapp.utils.DataDummy

class MoviesViewModel: ViewModel() {
    fun getMovies(): List<MoviesEntity> = DataDummy.generateMovies()
}