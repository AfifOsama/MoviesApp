package com.madman.moviesapp.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.vo.Resource
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
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesAppRepository: MoviesAppRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesAppRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovie = Resource.success(pagedList)
        Mockito.`when`(dummyMovie.data?.size).thenReturn(6)
        val movie = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        movie.value = dummyMovie

        Mockito.`when`(moviesAppRepository.getMovies()).thenReturn(movie)
        val movieEntity = viewModel.getMovies().value?.data
        Mockito.verify(moviesAppRepository).getMovies()
        assertNotNull(movieEntity)
        assertEquals(6, movieEntity?.size)

        viewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }
}