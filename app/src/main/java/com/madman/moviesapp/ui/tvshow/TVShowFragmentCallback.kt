package com.madman.moviesapp.ui.tvshow

import com.madman.moviesapp.data.resource.local.entity.TVShowEntity

interface TVShowFragmentCallback {
    fun onShareClick(TVshow: TVShowEntity)
}