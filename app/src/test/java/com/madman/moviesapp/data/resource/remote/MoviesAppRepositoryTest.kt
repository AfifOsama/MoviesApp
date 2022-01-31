package com.madman.moviesapp.data.resource.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.madman.moviesapp.data.resource.RemoteDataSource
import com.madman.moviesapp.data.resource.local.LocalDataSource
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.utils.AppExecutors
import com.madman.moviesapp.utils.DataDummy
import com.madman.moviesapp.utils.LiveDataTestUtil
import com.madman.moviesapp.utils.PagedListUtils
import com.madman.moviesapp.vo.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class MoviesAppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieAppRepository = FakeMoviesAppRepository(remote,local,appExecutors)

    private val listMovieEntity = DataDummy.generateMovies()


    private val listTvShowEntity = DataDummy.generateTVShow()


    private val movieEntity = DataDummy.generateMovies()[0]
    private val tvShowEntity = DataDummy.generateTVShow()[0]

    @Test
    fun getMovies() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        Mockito.`when`(local.getMovies()).thenReturn(dataSource)
        movieAppRepository.getMovies()

        val movieEntity = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateMovies()))
        Mockito.verify(local).getMovies()
        assertNotNull(movieEntity.data)
        assertEquals(listMovieEntity.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MoviesEntity>()
        dummyMovie.value = movieEntity
        Mockito.`when`(local.getMovieDetail(movieEntity.id)).thenReturn(dummyMovie)

        val movie = LiveDataTestUtil.getValue(movieAppRepository.getMovieDetail(movieEntity.id))
        Mockito.verify(local).getMovieDetail(movieEntity.id)
        assertNotNull(movie)
        assertEquals(movieEntity.title, movie.title)
    }

    @Test
    fun getTvShow() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        Mockito.`when`(local.getTvShows()).thenReturn(dataSource)
        movieAppRepository.getTvShows()
        val tvShowEntity = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateTVShow()))
        Mockito.verify(local).getTvShows()
        assertNotNull(tvShowEntity.data)
        assertEquals(listTvShowEntity.size.toLong(), tvShowEntity.data?.size?.toLong())
    }

    @Test
    fun getTvShowDetail() {
        val dummyTvShow = MutableLiveData<TVShowEntity>()
        dummyTvShow.value = tvShowEntity
        Mockito.`when`(local.getTvShowDetail(tvShowEntity.id)).thenReturn(dummyTvShow)

        val tvShow = LiveDataTestUtil.getValue(movieAppRepository.getTvShowDetail(tvShowEntity.id))
        Mockito.verify(local).getTvShowDetail(tvShowEntity.id)
        assertNotNull(tvShow)
        assertEquals(tvShowEntity.title, tvShow.title)
    }

    @Test
    fun testGetListFavoriteMovies() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        Mockito.`when`(local.getFavoriteMovies()).thenReturn(dataSource)
        movieAppRepository.getListFavoriteMovies()

        val movieEntity = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateMovies()))
        Mockito.verify(local).getFavoriteMovies()
        assertNotNull(movieEntity.data)
        assertEquals(listMovieEntity.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getListFavoriteTvShows() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        Mockito.`when`(local.getFavoriteTvShows()).thenReturn(dataSource)
        movieAppRepository.getListFavoriteTvShow()

        val tvShowEntity = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateTVShow()))
        Mockito.verify(local).getFavoriteTvShows()
        assertNotNull(tvShowEntity.data)
        assertEquals(listTvShowEntity.size.toLong(), tvShowEntity.data?.size?.toLong())
    }
}