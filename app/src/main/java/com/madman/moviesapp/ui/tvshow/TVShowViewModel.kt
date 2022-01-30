package com.madman.moviesapp.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.utils.DataDummy
import com.madman.moviesapp.vo.Resource

class TVShowViewModel(private val moviesAppRepository: MoviesAppRepository):ViewModel() {
    fun getTVShow(): LiveData<Resource<PagedList<TVShowEntity>>> = moviesAppRepository.getTvShows()
}