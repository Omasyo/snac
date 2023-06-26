package com.quitr.snac.core.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Named("IO")
    fun providesIODispatcher() : CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named("Default")
    fun providesDefaultDispatcher() : CoroutineDispatcher = Dispatchers.Default
}