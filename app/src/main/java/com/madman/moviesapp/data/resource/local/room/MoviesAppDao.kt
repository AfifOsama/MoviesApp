package com.madman.moviesapp.data.resource.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.madman.moviesapp.data.resource.local.entity.MoviesEntity
import com.madman.moviesapp.data.resource.local.entity.TVShowEntity

@Dao
interface MoviesAppDao {
    @Query("SELECT * FROM movie_entities")
    fun getMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM movie_entities WHERE is_favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM movie_entities WHERE movie_id = :movieId")
    fun getDetailMovie(movieId: Int): LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MoviesEntity::class)
    fun insertListMovies(movies: List<MoviesEntity>)

    @Update(entity = MoviesEntity::class)
    fun updateMovie(movie: MoviesEntity)

    @Query("SELECT * FROM tvshow_entities")
    fun getTvShows(): DataSource.Factory<Int, TVShowEntity>

    @Query("SELECT * FROM tvshow_entities WHERE is_favorite = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TVShowEntity>

    @Query("SELECT * FROM tvshow_entities WHERE tvshow_id = :tvShowId")
    fun getDetailTvShow(tvShowId: Int): LiveData<TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TVShowEntity::class)
    fun insertListTvShows(tvShows: List<TVShowEntity>)

    @Update(entity = TVShowEntity::class)
    fun updateTvShow(tvShows: TVShowEntity)
}