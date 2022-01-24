package com.madman.moviesapp.di

import com.madman.moviesapp.data.resource.RemoteDataSource
import com.madman.moviesapp.data.resource.remote.MoviesAppRepository

object Injection {
    fun provideRepository(): MoviesAppRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MoviesAppRepository.getInstance(remoteDataSource)
    }
}