package com.madman.moviesapp.data.resource.remote.api

import com.madman.moviesapp.data.resource.remote.response.ListResponse
import com.madman.moviesapp.data.resource.remote.response.MovieResponse
import com.madman.moviesapp.data.resource.remote.response.TVShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getMovies(
        @Query("api_key") apiKey: String = "bc87bffad5724e1e5ab6bf382d23f031"
    ): Call<ListResponse<MovieResponse>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "bc87bffad5724e1e5ab6bf382d23f031"
    ): Call<MovieResponse>

    @GET("tv/on_the_air")
    fun getTvShow(
        @Query("api_key") apiKey: String = "bc87bffad5724e1e5ab6bf382d23f031"
    ): Call<ListResponse<TVShowResponse>>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = "bc87bffad5724e1e5ab6bf382d23f031"
    ): Call<TVShowResponse>
}