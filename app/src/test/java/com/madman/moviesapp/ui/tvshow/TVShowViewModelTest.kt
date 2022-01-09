package com.madman.moviesapp.ui.tvshow

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
        val tvShowEntities = viewModel.getTVShow()
        assertNotNull(tvShowEntities)
        assertEquals(numberOfList, tvShowEntities.size)
    }
}