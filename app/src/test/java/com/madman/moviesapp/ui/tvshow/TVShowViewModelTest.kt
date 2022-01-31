package com.madman.moviesapp.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.ui.movies.MoviesViewModel
import com.madman.moviesapp.utils.DataDummy
import com.madman.moviesapp.vo.Resource
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVShowViewModelTest {
    private lateinit var viewModel: TVShowViewModel

    //perlu ditambahkan karena pengujiannya berupa proses asynchronous
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesAppRepository: MoviesAppRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TVShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TVShowEntity>

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(moviesAppRepository)
    }

    @Test
    fun getTVShow() {
        val dummyTvShow = Resource.success(pagedList)
        Mockito.`when`(dummyTvShow.data?.size).thenReturn(6)
        val tvShow = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        tvShow.value = dummyTvShow

        Mockito.`when`(moviesAppRepository.getTvShows()).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTVShow().value?.data
        Mockito.verify(moviesAppRepository).getTvShows()
        assertNotNull(tvShowEntity)
        assertEquals(6, tvShowEntity?.size)

        viewModel.getTVShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}