package com.madman.moviesapp.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.ui.favorite.movie.FavoriteMoviesViewModel
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowsViewModelTest{
    private lateinit var viewModel: FavoriteTvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MoviesAppRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TVShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TVShowEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowsViewModel(repository)
    }

    @Test
    fun getListFavoriteTvShows() {
        val dummyTvShow = pagedList
        Mockito.`when`(dummyTvShow.size).thenReturn(6)
        val tvShow = MutableLiveData<PagedList<TVShowEntity>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(repository.getListFavoriteTvShow()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getListFavoriteTvShows().value
        Mockito.verify(repository).getListFavoriteTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(6, tvShowEntity?.size)

        viewModel.getListFavoriteTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)

    }

}