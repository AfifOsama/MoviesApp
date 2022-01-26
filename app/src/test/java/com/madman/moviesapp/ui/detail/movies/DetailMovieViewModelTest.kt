package com.madman.moviesapp.ui.detail.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.ui.detail.tvshow.DetailTvshowViewModel
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
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateMovies()[0]
    private val movieId=dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesAppRepository: MoviesAppRepository

    @Mock
    private lateinit var movieObserver: Observer<MoviesEntity>

    @Before
    fun setUp(){
        viewModel= DetailMovieViewModel(moviesAppRepository)
    }

    @Test
    fun getMovies() {
        val movie=MutableLiveData<MoviesEntity>()
        movie.value=dummyMovie
        Mockito.`when`(moviesAppRepository.getMovieDetail(movieId)).thenReturn(movie)
        val movieEntity=viewModel.getMovie(movieId).value as MoviesEntity
        Mockito.verify<MoviesAppRepository>(moviesAppRepository).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id,movieEntity.id)
        assertEquals(dummyMovie.title,movieEntity.title)
        assertEquals(dummyMovie.description,movieEntity.description)
        assertEquals(dummyMovie.releaseDate,movieEntity.releaseDate)
        assertEquals(dummyMovie.imgPosterPath,movieEntity.imgPosterPath)
        assertEquals(dummyMovie.imgPreviewPath,movieEntity.imgPreviewPath)

        viewModel.getMovie(movieId).observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(dummyMovie)
    }

}