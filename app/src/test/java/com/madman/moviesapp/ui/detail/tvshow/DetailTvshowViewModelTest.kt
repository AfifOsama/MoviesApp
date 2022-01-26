package com.madman.moviesapp.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
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
class DetailTvshowViewModelTest {
    private lateinit var viewModel: DetailTvshowViewModel
    private val dummyTvshow = DataDummy.generateTVShow()[0]
    private val tvShowId=dummyTvshow.id

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesAppRepository: MoviesAppRepository

    @Mock
    private lateinit var tvShowObserver: Observer<TVShowEntity>

    @Before
    fun setUp(){
        viewModel= DetailTvshowViewModel(moviesAppRepository)
    }

    @Test
    fun getMovie() {
        val tvShow=MutableLiveData<TVShowEntity>()
        tvShow.value=dummyTvshow
        Mockito.`when`(moviesAppRepository.getTvShowDetail(tvShowId)).thenReturn(tvShow)
        val tvShowEntity=viewModel.getTVshow(tvShowId).value as TVShowEntity
        Mockito.verify<MoviesAppRepository>(moviesAppRepository).getTvShowDetail(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvshow.id,tvShowEntity.id)
        assertEquals(dummyTvshow.title,tvShowEntity.title)
        assertEquals(dummyTvshow.description,tvShowEntity.description)
        assertEquals(dummyTvshow.releaseDate,tvShowEntity.releaseDate)
        assertEquals(dummyTvshow.imgPosterPath,tvShowEntity.imgPosterPath)
        assertEquals(dummyTvshow.imgPreviewPath,tvShowEntity.imgPreviewPath)

        viewModel.getTVshow(tvShowId).observeForever(tvShowObserver)
        Mockito.verify(tvShowObserver).onChanged(dummyTvshow)
    }
}