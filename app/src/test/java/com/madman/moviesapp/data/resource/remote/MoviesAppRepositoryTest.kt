package com.madman.moviesapp.data.resource.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.madman.moviesapp.data.resource.RemoteDataSource
import com.madman.moviesapp.utils.DataDummy
import com.madman.moviesapp.utils.LiveDataTestUtil
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
import org.mockito.Mockito.mock

class MoviesAppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieAppRepository = FakeMoviesAppRepository(remote)

    private val listMovieResponse = DataDummy.generateRemoteDummyMovies()
    private val movieId = listMovieResponse[0].id

    private val listTvShowResponse = DataDummy.generateRemoteDummyTvShow()
    private val tvShowId = listTvShowResponse[0].id

    private val movieResponse = DataDummy.generateRemoteDummyMovies()[0]
    private val tvShowResponse = DataDummy.generateRemoteDummyTvShow()[0]

    @Test
    fun getMovies() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(
                    listMovieResponse
                )
                null
            }.`when`(remote).getNowPlayingMovies(any())
        }

        val movieEntity = LiveDataTestUtil.getValue(movieAppRepository.getMovies())

        runBlocking {
            verify(remote).getNowPlayingMovies(any())
        }

        assertNotNull(movieEntity)
        assertEquals(listMovieResponse.size.toLong(), movieEntity.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailReceived(movieResponse)
                null
            }.`when`(remote).getMovieDetail(eq(movieId), any())
        }

        val movieEntity = LiveDataTestUtil.getValue(movieAppRepository.getMovieDetail(movieId))

        runBlocking {
            verify(remote).getMovieDetail(eq(movieId), any())
        }

        assertNotNull(movieEntity)
        assertEquals(movieResponse.id, movieEntity.id)
    }

    @Test
    fun getTvShow() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback).onAllTvShowsReceived(
                    listTvShowResponse
                )
                null
            }.`when`(remote).getNowPlayingMovies(any())
        }

        val tvShowEntity = LiveDataTestUtil.getValue(movieAppRepository.getMovies())

        runBlocking {
            verify(remote).getTvShow(any())
        }

        assertNotNull(tvShowEntity)
        assertEquals(listTvShowResponse.size.toLong(), tvShowEntity.size.toLong())
    }

    @Test
    fun getTvShowDetail() {
        runBlocking {
            doAnswer {invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailReceived(tvShowResponse)
                null
            }.`when`(remote).getTvShowDetail(eq(tvShowId), any())
        }

        val tvShowEntity = LiveDataTestUtil.getValue(movieAppRepository.getTvShowDetail(tvShowId))

        runBlocking {
            verify(remote).getMovieDetail(eq(movieId), any())
        }

        assertNotNull(tvShowEntity)
        assertEquals(movieResponse.id, tvShowEntity.id)
    }
}