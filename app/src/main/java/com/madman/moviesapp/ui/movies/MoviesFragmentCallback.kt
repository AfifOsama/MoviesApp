package com.madman.moviesapp.ui.movies

import com.madman.moviesapp.data.resource.local.entity.MoviesEntity

interface MoviesFragmentCallback {
    fun onShareClick(movies: MoviesEntity)
}