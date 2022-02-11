package com.madman.moviesapp.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("status_message")
    val statusMessage: String? = null,
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("results")
    val result: List<MovieResponse>? = null
)

data class ListTvShowResponse(
    @SerializedName("status_message")
    val statusMessage: String? = null,
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("results")
    val result: List<TVShowResponse>? = null
)