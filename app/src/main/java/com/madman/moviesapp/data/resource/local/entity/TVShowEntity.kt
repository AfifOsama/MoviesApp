package com.madman.moviesapp.data.resource.local.entity

class TVShowEntity(
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var releaseDate: String? = null,
    var score: Double? = null,
    var imgPosterPath: String? = null,
    var imgPreviewPath: String? = null
)