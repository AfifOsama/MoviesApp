package com.madman.moviesapp.di

import android.content.Context
import com.madman.moviesapp.data.resource.RemoteDataSource
import com.madman.moviesapp.data.resource.local.LocalDataSource
import com.madman.moviesapp.data.resource.local.room.MoviesAppDatabase
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository
import com.madman.moviesapp.utils.AppExecutors

object Injection {
    fun provideRepository(context:Context): MoviesAppRepository {
        val database = MoviesAppDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.moviesAppDao())
        val appExecutors = AppExecutors()
        return MoviesAppRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}