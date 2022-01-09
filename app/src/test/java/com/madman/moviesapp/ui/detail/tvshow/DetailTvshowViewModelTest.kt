package com.madman.moviesapp.ui.detail.tvshow

import com.madman.moviesapp.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailTvshowViewModelTest {
    private lateinit var viewModel: DetailTvshowViewModel
    private val dummyTvshow = DataDummy.generateTVShow()[0]
    private val tvShowName = dummyTvshow.title

    @Before
    fun setUp() {
        viewModel = DetailTvshowViewModel()
        viewModel.selectedTVshow(tvShowName)
    }

    @Test
    fun getMovies() {
        viewModel.selectedTVshow(dummyTvshow.title)
        val tvShowEntity = viewModel.getTVshow()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvshow.title, tvShowEntity.title)
        assertEquals(dummyTvshow.description, tvShowEntity.description)
        assertEquals(dummyTvshow.rating, tvShowEntity.rating)
        assertEquals(dummyTvshow.director, tvShowEntity.director)
        assertEquals(dummyTvshow.genre, tvShowEntity.genre)
        assertEquals(dummyTvshow.releaseDate, tvShowEntity.releaseDate)
        assertEquals(dummyTvshow.score, tvShowEntity.score)
        assertEquals(dummyTvshow.imgPath, tvShowEntity.imgPath)
    }
}