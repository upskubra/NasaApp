package com.kubrayildirim.nasaapp.data.repository

import com.kubrayildirim.nasaapp.data.model.RoverModel
import com.kubrayildirim.nasaapp.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RoverRepository {
    suspend fun fetchCuriosity(): Flow<NetworkResult<RoverModel>>
    suspend fun fetchOpportunity(): Flow<NetworkResult<RoverModel>>
    suspend fun fetchSpirit(): Flow<NetworkResult<RoverModel>>
}