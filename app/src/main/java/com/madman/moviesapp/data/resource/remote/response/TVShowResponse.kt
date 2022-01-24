package com.madman.moviesapp.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class TVShowResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("original_title")
    var title: String? = null,
    @SerializedName("overview")
    var description: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    @SerializedName("vote_average")
    var score: Double? = 0.0,
    @SerializedName("poster_path")
    var img_poster: String? = null,
    @SerializedName("backdrop_path")
    var img_preview: String? = null
)