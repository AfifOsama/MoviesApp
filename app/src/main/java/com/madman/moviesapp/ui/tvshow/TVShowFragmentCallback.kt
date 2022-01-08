package com.madman.moviesapp.ui.tvshow

import com.madman.moviesapp.data.TVShowEntity

interface TVShowFragmentCallback {
    fun onShareClick(TVshow: TVShowEntity)
}