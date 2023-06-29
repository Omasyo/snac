package com.quitr.snac.core.data.di

import com.quitr.snac.core.data.movie.DefaultMovieRepository
import com.quitr.snac.core.data.movie.MovieRepository
import com.quitr.snac.core.data.people.DefaultPeopleRepository
import com.quitr.snac.core.data.people.PeopleRepository
import com.quitr.snac.core.data.tv.DefaultTvRepository
import com.quitr.snac.core.data.tv.TvRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
private interface RepositoryModule{
    @Binds
    fun bindMovieRepository(movieRepository: DefaultMovieRepository) : MovieRepository

    @Binds
    fun bindTvRepository(tvRepository: DefaultTvRepository) : TvRepository

    @Binds
    fun bindPeopleRepository(peopleRepository: DefaultPeopleRepository) : PeopleRepository
}
