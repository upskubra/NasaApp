package com.kubrayildirim.nasaapp.ui.curiosity

import android.app.Application
import com.kubrayildirim.nasaapp.data.model.RoverViewState
import com.kubrayildirim.nasaapp.data.remote.NetworkResult
import com.kubrayildirim.nasaapp.data.repository.RoverRepository
import com.kubrayildirim.nasaapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CuriosityViewModel @Inject constructor(
    private val roverRepository: RoverRepository,
    application: Application
) : BaseViewModel(application) {
    private val _curiosityState = MutableStateFlow(RoverViewState())
    val curiosityState: StateFlow<RoverViewState> = _curiosityState.asStateFlow()

    suspend fun getCuriosity() {
        roverRepository.fetchCuriosity().collect { result ->
            when (result) {
                is NetworkResult.Success -> {
                    val cameraList = HashSet<String>()
                    result.data?.let { roverModel ->
                        roverModel.photos.forEach {
                            cameraList.add(it.camera.name)
                        }
                        RoverViewState(
                            photoList = roverModel,
                            isLoading = false,
                            cameraList = cameraList
                        )
                    }!!.also { _curiosityState.value = it }
                }
                is NetworkResult.Error -> {
                    _curiosityState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
                is NetworkResult.Loading -> {
                    _curiosityState.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }
}
