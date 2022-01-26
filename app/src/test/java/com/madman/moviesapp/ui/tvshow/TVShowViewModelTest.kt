package com.madman.moviesapp.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.ui.movies.MoviesViewModel
import com.madman.moviesapp.utils.DataDummy
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
    private lateinit var observer: Observer<List<TVShowEntity>>

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(moviesAppRepository)
    }

    @Test
    fun getTVShow() {
        val dummyTvShow = DataDummy.generateTVShow()
        val tvShow = MutableLiveData<List<TVShowEntity>>()
        tvShow.value = dummyTvShow
        Mockito.`when`(moviesAppRepository.getTvShow()).then { tvShow }
        val tvShowEntity = viewModel.getTVShow().value
        Mockito.verify<MoviesAppRepository>(moviesAppRepository).getTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(10, tvShowEntity?.size)

        viewModel.getTVShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShow)
    }
}