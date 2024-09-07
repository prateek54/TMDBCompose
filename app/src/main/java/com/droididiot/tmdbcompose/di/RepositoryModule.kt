package com.droididiot.tmdbcompose.di

import com.droididiot.tmdbcompose.data.MovieRepository
import com.droididiot.tmdbcompose.data.TMDBApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(api: TMDBApiService): MovieRepository {
        return MovieRepository(api)
    }
}