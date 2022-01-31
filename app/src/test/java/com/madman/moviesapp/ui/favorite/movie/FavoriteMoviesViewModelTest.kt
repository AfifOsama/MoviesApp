package com.madman.moviesapp.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
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
class FavoriteMoviesViewModelTest {
    private lateinit var viewModel: FavoriteMoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MoviesAppRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteMoviesViewModel(repository)
    }

    @Test
    fun getListFavoriteMovies() {
        val dummyMovie = pagedList
        Mockito.`when`(dummyMovie.size).thenReturn(6)
        val movie = MutableLiveData<PagedList<MoviesEntity>>()
        movie.value = dummyMovie

        Mockito.`when`(repository.getListFavoriteMovies()).thenReturn(movie)
        val movieEntity = viewModel.getListFavoriteMovies().value
        Mockito.verify(repository).getListFavoriteMovies()
        assertNotNull(movieEntity)
        assertEquals(6, movieEntity?.size)

        viewModel.getListFavoriteMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)

    }

}