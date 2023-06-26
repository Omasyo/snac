package com.quitr.snac.core.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule{
    @Binds
    fun bindMovieRepository(movieRepository: DefaultMovieRepository) : MovieRepository

    @Binds
    fun bindTvRepository(tvRepository: DefaultTvRepository) : TvRepository
}
