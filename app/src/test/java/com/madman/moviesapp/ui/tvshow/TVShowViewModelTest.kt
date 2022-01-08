package com.madman.moviesapp.ui.tvshow

import com.madman.moviesapp.ui.movies.MoviesViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TVShowViewModelTest {
    private lateinit var viewModel: TVShowViewModel
    private val numberOfList = 10

    @Before
    fun setUp() {
        viewModel = TVShowViewModel()
    }

    @Test
    fun getTVShow() {
        val TVShowEntities = viewModel.getTVShow()
        assertNotNull(TVShowEntities)
        assertEquals(numberOfList, TVShowEntities.size)
    }
}