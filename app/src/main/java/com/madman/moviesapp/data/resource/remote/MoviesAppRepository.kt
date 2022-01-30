package com.madman.moviesapp.data.resource.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.madman.moviesapp.data.resource.RemoteDataSource
import com.madman.moviesapp.data.resource.local.LocalDataSource
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.response.MovieResponse
import com.madman.moviesapp.data.resource.remote.response.TVShowResponse
import com.madman.moviesapp.utils.AppExecutors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesAppRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MoviesAppDataStore {

    override fun getMovies(): LiveData<List<MoviesEntity>> {
        val listMovieResult = MutableLiveData<List<MoviesEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
                override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                    val movieList = ArrayList<MoviesEntity>()
                    for (response in movieResponse) {
                        val movie = MoviesEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.releaseDate,
                            response.score,
                            response.img_poster,
                            response.img_preview
                        )
                        movieList.add(movie)
                    }
                    listMovieResult.postValue(movieList)
                }
            })
        }

        return listMovieResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<MoviesEntity> {
        val movieResult = MutableLiveData<MoviesEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieDetail(
                movieId,
                object : RemoteDataSource.LoadMovieDetailCallback {
                    override fun onMovieDetailReceived(movieResponse: MovieResponse) {
                        val movieDetail = MoviesEntity(
                            movieResponse.id,
                            movieResponse.title,
                            movieResponse.description,
                            movieResponse.releaseDate,
                            movieResponse.score,
                            movieResponse.img_poster,
                            movieResponse.img_preview
                        )

                        movieResult.postValue(movieDetail)
                    }
                })
        }
        return movieResult
    }

    override fun getTvShow(): LiveData<List<TVShowEntity>> {
        val listTvShowResult = MutableLiveData<List<TVShowEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShow(object : RemoteDataSource.LoadTvShowCallback {
                override fun onAllTvShowsReceived(tvShowResponse: List<TVShowResponse>) {
                    val tvShowList = ArrayList<TVShowEntity>()
                    for (response in tvShowResponse) {
                        val tvShow = TVShowEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.releaseDate,
                            response.score,
                            response.img_poster,
                            response.img_preview
                        )
                        tvShowList.add(tvShow)
                    }

                    listTvShowResult.postValue(tvShowList)
                }
            })
        }

        return listTvShowResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TVShowEntity> {
        val tvShowResult = MutableLiveData<TVShowEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShowDetail(
                tvShowId,
                object : RemoteDataSource.LoadTvShowDetailCallback {
                    override fun onTvShowDetailReceived(tvShowResponse: TVShowResponse) {
                        val tvShow = TVShowEntity(
                            tvShowResponse.id,
                            tvShowResponse.title,
                            tvShowResponse.description,
                            tvShowResponse.releaseDate,
                            tvShowResponse.score,
                            tvShowResponse.img_poster,
                            tvShowResponse.img_preview
                        )

                        tvShowResult.postValue(tvShow)
                    }
                })
        }

        return tvShowResult
    }

    companion object {
        @Volatile
        private var instance: MoviesAppRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): MoviesAppRepository =
            instance ?: synchronized(this) {
                instance ?: MoviesAppRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }
}