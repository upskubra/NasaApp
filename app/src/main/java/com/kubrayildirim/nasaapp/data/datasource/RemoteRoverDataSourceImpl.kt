package com.kubrayildirim.nasaapp.data.datasource

import com.kubrayildirim.nasaapp.data.model.RoverModel
import com.kubrayildirim.nasaapp.data.remote.NasaApiService
import com.kubrayildirim.nasaapp.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class RemoteRoverDataSourceImpl @Inject constructor(private val nasaApiService: NasaApiService) :
    RemoteRoverDataSource, BaseRemoteDataSource() {
    override suspend fun fetchCuriosity(): Flow<NetworkResult<RoverModel>> {
        return getResult {
            nasaApiService.fetchCuriosity()
        }
    }

    override suspend fun fetchOpportunity(): Flow<NetworkResult<RoverModel>> {
        return getResult {
            nasaApiService.fetchOpportunity()
        }
    }

    override suspend fun fetchSpirit(): Flow<NetworkResult<RoverModel>> {
        return getResult {
            nasaApiService.fetchSpirit()
        }
    }
}