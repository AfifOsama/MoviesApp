package com.madman.moviesapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.di.Injection
import com.madman.moviesapp.ui.movies.MoviesViewModel

class ViewModelFactory private constructor(private val mMoviesAppRepository: MoviesAppRepository) :
    ViewModelProvider.NewInstanceFactory() {

//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        when {
//            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
//                return MoviesViewModel(mMoviesAppRepository) as T
//            }
//            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> {
//                return DetailCourseViewModel(mMoviesAppRepository) as T
//            }
//            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
//                return BookmarkViewModel(mMoviesAppRepository) as T
//            }
//            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
//                return CourseReaderViewModel(mMoviesAppRepository) as T
//            }
//            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
//        }
//
//    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

}