package com.quitr.snac.core.data.di

import com.quitr.snac.core.data.repository.movie.DefaultMovieRepository
import com.quitr.snac.core.data.repository.movie.MovieRepository
import com.quitr.snac.core.data.repository.people.DefaultPeopleRepository
import com.quitr.snac.core.data.repository.people.PeopleRepository
import com.quitr.snac.core.data.repository.search.DefaultSearchRepository
import com.quitr.snac.core.data.repository.search.SearchRepository
import com.quitr.snac.core.data.repository.tv.DefaultTvRepository
import com.quitr.snac.core.data.repository.tv.TvRepository
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

    @Binds
    fun bindSearchRepository(searchRepository: DefaultSearchRepository) : SearchRepository
}
