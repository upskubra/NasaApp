package com.kubrayildirim.nasaapp.data.datasource

import com.kubrayildirim.nasaapp.data.remote.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response

open class BaseRemoteDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Flow<NetworkResult<T>> {
        return flow<NetworkResult<T>> {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) emit(NetworkResult.Success(body))
            } else {
                emit(NetworkResult.Error(response.errorBody().toString()))
            }
        }.catch {
            emit(NetworkResult.Error(it.message ?: it.toString()))
        }.onStart {
            emit(NetworkResult.Loading())
        }.flowOn(Dispatchers.IO)
    }
}