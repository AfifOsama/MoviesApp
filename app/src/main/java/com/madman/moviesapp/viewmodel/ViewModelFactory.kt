package com.madman.moviesapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.di.Injection
import com.madman.moviesapp.ui.detail.movies.DetailMovieViewModel
import com.madman.moviesapp.ui.detail.tvshow.DetailTvshowViewModel
import com.madman.moviesapp.ui.favorite.movie.FavoriteMoviesViewModel
import com.madman.moviesapp.ui.favorite.tvshow.FavoriteTvShowsViewModel
import com.madman.moviesapp.ui.movies.MoviesViewModel
import com.madman.moviesapp.ui.tvshow.TVShowViewModel

class ViewModelFactory private constructor(private val mMoviesAppRepository: MoviesAppRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                return MoviesViewModel(mMoviesAppRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                return DetailMovieViewModel(mMoviesAppRepository) as T
            }
            modelClass.isAssignableFrom(TVShowViewModel::class.java) -> {
                return TVShowViewModel(mMoviesAppRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvshowViewModel::class.java) -> {
                return DetailTvshowViewModel(mMoviesAppRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java) -> {
                return FavoriteMoviesViewModel(mMoviesAppRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowsViewModel::class.java) -> {
                return FavoriteTvShowsViewModel(mMoviesAppRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

}