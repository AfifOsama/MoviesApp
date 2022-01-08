package com.madman.moviesapp.ui.tvshow

import androidx.lifecycle.ViewModel
import com.madman.moviesapp.data.TVShowEntity
import com.madman.moviesapp.utils.DataDummy

class TVShowViewModel:ViewModel() {
    fun getTVShow(): List<TVShowEntity> = DataDummy.generateTVShow()
}