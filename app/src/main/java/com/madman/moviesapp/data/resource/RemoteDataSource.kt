package com.madman.moviesapp.data.resource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.madman.moviesapp.data.resource.remote.api.ApiService
import com.madman.moviesapp.data.resource.remote.response.MovieResponse
import com.madman.moviesapp.data.resource.remote.response.TVShowResponse
import com.madman.moviesapp.data.resource.remote.vo.ApiResponse
import com.madman.moviesapp.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class RemoteDataSource constructor(private val apiService: ApiService) {
    fun getMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultResponse = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getMovies().await()
                resultResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultResponse
    }

    suspend fun getTvShow(): LiveData<ApiResponse<List<TVShowResponse>>> {
        EspressoIdlingResource.increment()
        val resultResponse = MutableLiveData<ApiResponse<List<TVShowResponse>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getTvShow().await()
                resultResponse.postValue(ApiResponse.success(response.result!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultResponse
    }


    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
    }
}