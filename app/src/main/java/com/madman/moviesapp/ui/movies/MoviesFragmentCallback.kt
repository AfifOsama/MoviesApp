package com.madman.moviesapp.ui.movies

import com.madman.moviesapp.data.MoviesEntity

interface MoviesFragmentCallback {
    fun onShareClick(movies: MoviesEntity)
}