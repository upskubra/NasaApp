package com.kubrayildirim.nasaapp.data.repository

import com.kubrayildirim.nasaapp.data.datasource.RemoteRoverDataSource
import com.kubrayildirim.nasaapp.data.model.RoverModel
import com.kubrayildirim.nasaapp.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoverRepositoryImpl @Inject constructor(private val remoteRoverDataSource: RemoteRoverDataSource) :
    RoverRepository {
    override suspend fun fetchCuriosity(): Flow<NetworkResult<RoverModel>> =
        remoteRoverDataSource.fetchCuriosity()


    override suspend fun fetchOpportunity(): Flow<NetworkResult<RoverModel>> =
        remoteRoverDataSource.fetchOpportunity()

    override suspend fun fetchSpirit(): Flow<NetworkResult<RoverModel>> =
        remoteRoverDataSource.fetchSpirit()
}
