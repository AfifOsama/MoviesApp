package com.madman.moviesapp.data.resource.remote

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.madman.moviesapp.data.NetworkBoundResource
import com.madman.moviesapp.data.resource.RemoteDataSource
import com.madman.moviesapp.data.resource.local.LocalDataSource
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity
import com.madman.moviesapp.data.resource.remote.response.MovieResponse
import com.madman.moviesapp.data.resource.remote.response.TVShowResponse
import com.madman.moviesapp.data.resource.remote.vo.ApiResponse
import com.madman.moviesapp.utils.AppExecutors
import com.madman.moviesapp.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesAppRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MoviesAppDataStore {

    override fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MoviesEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MoviesEntity>()
                for (response in data) {
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
                localDataSource.insertListMovies(movieList)
            }
        }.asLiveData()

    }

    override fun getTvShows(): LiveData<Resource<PagedList<TVShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TVShowEntity>, List<TVShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TVShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TVShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TVShowResponse>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<TVShowResponse>) {
                val tvShowList = ArrayList<TVShowEntity>()
                for (response in data) {
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
                localDataSource.insertListTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<MoviesEntity> =
        localDataSource.getMovieDetail(movieId)

    override fun getTvShowDetail(tvShowId: Int): LiveData<TVShowEntity> =
        localDataSource.getTvShowDetail(tvShowId)

    override fun setFavoriteMovie(movie: MoviesEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteMovie(movie)
        }
    }

    override fun getListFavoriteMovies(): LiveData<PagedList<MoviesEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(5)
            setPageSize(5)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TVShowEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteTvShow(tvShow)
        }
    }

    override fun getListFavoriteTvShow(): LiveData<PagedList<TVShowEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(5)
            setPageSize(5)
        }.build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    companion object {
        @Volatile
        private var instance: MoviesAppRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MoviesAppRepository =
            instance ?: synchronized(this) {
                instance ?: MoviesAppRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }
}