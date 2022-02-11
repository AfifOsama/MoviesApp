package com.madman.moviesapp.data.resource.remote.api

import com.madman.moviesapp.data.resource.remote.response.ListMovieResponse
import com.madman.moviesapp.data.resource.remote.response.ListTvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getMovies(
        @Query("api_key") apiKey: String = "bc87bffad5724e1e5ab6bf382d23f031"
    ): Call<ListMovieResponse>

    @GET("tv/on_the_air")
    fun getTvShow(
        @Query("api_key") apiKey: String = "bc87bffad5724e1e5ab6bf382d23f031"
    ): Call<ListTvShowResponse>

}