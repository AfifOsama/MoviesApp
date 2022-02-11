package com.madman.moviesapp.data.resource.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tvshow_entities")
@Parcelize
class TVShowEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null,
    @ColumnInfo(name = "score")
    var score: Double? = null,
    @ColumnInfo(name = "img_poster_path")
    var imgPosterPath: String? = null,
    @ColumnInfo(name = "img_preview_path")
    var imgPreviewPath: String? = null,
    @NonNull
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
) : Parcelable