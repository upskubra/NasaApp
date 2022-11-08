package com.kubrayildirim.nasaapp.di

import com.kubrayildirim.nasaapp.data.datasource.RemoteRoverDataSource
import com.kubrayildirim.nasaapp.data.datasource.RemoteRoverDataSourceImpl
import com.kubrayildirim.nasaapp.data.remote.NasaApiService
import com.kubrayildirim.nasaapp.data.repository.RoverRepository
import com.kubrayildirim.nasaapp.data.repository.RoverRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesApiRemoteDataSource(nasaApiService: NasaApiService): RemoteRoverDataSource {
        return RemoteRoverDataSourceImpl(nasaApiService)
    }

    @Provides
    fun providesApiRemoteRepository(remoteRoverDataSource: RemoteRoverDataSource): RoverRepository {
        return RoverRepositoryImpl(remoteRoverDataSource)
    }
}