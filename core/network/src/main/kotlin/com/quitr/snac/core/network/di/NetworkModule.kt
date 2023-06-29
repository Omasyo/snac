package com.quitr.snac.core.network.di

import com.quitr.snac.core.network.createClient
import com.quitr.snac.core.network.movie.DefaultMovieNetworkDataSource
import com.quitr.snac.core.network.movie.MovieNetworkDataSource
import com.quitr.snac.core.network.people.DefaultPeopleNetworkDataSource
import com.quitr.snac.core.network.people.PeopleNetworkDataSource
import com.quitr.snac.core.network.tv.DefaultTvNetworkDataSource
import com.quitr.snac.core.network.tv.TvNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient() = createClient()

    @Provides
    @Singleton
    fun provideMovieNetworkDataSource(client: HttpClient): MovieNetworkDataSource =
        DefaultMovieNetworkDataSource(client)

    @Provides
    @Singleton
    fun provideNetworkDataSource(client: HttpClient): TvNetworkDataSource =
        DefaultTvNetworkDataSource(client)

    @Provides
    @Singleton
    fun providePeopleDataSource(client: HttpClient): PeopleNetworkDataSource =
        DefaultPeopleNetworkDataSource(client)
}