package com.madman.moviesapp.data.resource.remote.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private val httpClient = OkHttpClient.Builder().apply {
    }.build()

    fun createApi(): ApiService {
        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

}