package com.madman.moviesapp.ui.detail.movies

import com.madman.moviesapp.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovies = DataDummy.generateMovies()[0]
    private val movieName = dummyMovies.title

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.selectedMovie(movieName)
    }

    @Test
    fun getMovies() {
        viewModel.selectedMovie(dummyMovies.title)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovies.title, movieEntity.title)
        assertEquals(dummyMovies.description, movieEntity.description)
        assertEquals(dummyMovies.rating, movieEntity.rating)
        assertEquals(dummyMovies.director, movieEntity.director)
        assertEquals(dummyMovies.genre, movieEntity.genre)
        assertEquals(dummyMovies.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovies.score, movieEntity.score)
        assertEquals(dummyMovies.imgPath, movieEntity.imgPath)
    }

}