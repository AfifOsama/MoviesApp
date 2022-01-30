package com.madman.moviesapp.data.resource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.madman.moviesapp.data.resource.remote.api.ApiConfig
import com.madman.moviesapp.data.resource.remote.api.ApiService
import com.madman.moviesapp.data.resource.remote.response.*
import com.madman.moviesapp.data.resource.remote.vo.ApiResponse
import com.madman.moviesapp.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RemoteDataSource {
    private val apiConfig = ApiConfig
    fun getMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        apiConfig.createApi().getMovies().enqueue(object : Callback<ListMovieResponse> {
            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                val result = response.body()?.result
                if (result != null) {
                    val add = ApiResponse.success(result)
                    movies.postValue(add)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return movies

    }

    fun getTvShows(): LiveData<ApiResponse<List<TVShowResponse>>> {
        EspressoIdlingResource.increment()
        val tvShows = MutableLiveData<ApiResponse<List<TVShowResponse>>>()
        apiConfig.createApi().getTvShow().enqueue(object : Callback<ListTvShowResponse> {
            override fun onResponse(
                call: Call<ListTvShowResponse>,
                response: Response<ListTvShowResponse>
            ) {
                val result = response.body()?.result
                if (result!=null) {
                    tvShows.postValue(ApiResponse.success(result))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ListTvShowResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })

        return tvShows
    }

    fun getMovieDetail(moviesId: Int): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val movie = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        apiConfig.createApi().getDetailMovie(moviesId).enqueue(object : Callback<ListMovieResponse> {
            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                val result = response.body()?.result
                if (result != null) {
                    val add = ApiResponse.success(result)
                    movie.postValue(add)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return movie
    }

    fun getTvShowDetail(tvShowsId: Int): LiveData<ApiResponse<List<TVShowResponse>>> {
        EspressoIdlingResource.increment()
        val tvShow = MutableLiveData<ApiResponse<List<TVShowResponse>>>()
        apiConfig.createApi().getDetailTvShow(tvShowsId)
            .enqueue(object : Callback<ListTvShowResponse> {
                override fun onResponse(
                    call: Call<ListTvShowResponse>,
                    response: Response<ListTvShowResponse>
                ) {
                    val result = response.body()?.result
                    if (result!=null) {
                        tvShow.postValue(ApiResponse.success(result))
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ListTvShowResponse>, t: Throwable) {
                    EspressoIdlingResource.decrement()
                }
            })
        return tvShow
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}