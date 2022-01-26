package com.madman.moviesapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.madman.moviesapp.R

object GlideHelper {
    const val API_IMG_ENDPOINT = "https://image.tmdb.org/t/p/"
    const val ENDPOINT_IMG_SIZE_W185 = "w185"
    const val ENDPOINT_IMG_SIZE_W780 = "w780"

    fun glideImage(context: Context, path: String, imgView: ImageView) {
        Glide.with(context).clear(imgView)
        Glide.with(context).load(path).apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error).into(imgView)
    }
}