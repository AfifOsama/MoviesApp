package com.madman.moviesapp.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
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
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    //perlu ditambahkan karena pengujiannya berupa proses asynchronous
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesAppRepository: MoviesAppRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesEntity>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(moviesAppRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.generateMovies()
        val movies = MutableLiveData<List<MoviesEntity>>()
        movies.value = dummyMovies
        Mockito.`when`(moviesAppRepository.getMovies()).then { movies }
        val moviesEntity = viewModel.getMovies().value
        Mockito.verify<MoviesAppRepository>(moviesAppRepository).getMovies()
        assertNotNull(moviesEntity)
        assertEquals(10, moviesEntity?.size)

        viewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }
}