package com.kubrayildirim.nasaapp.ui.spirit

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
class SpiritViewModel @Inject constructor(
    private val roverRepository: RoverRepository,
    application: Application
) : BaseViewModel(application) {
    private val _spiritState = MutableStateFlow(RoverViewState())
    val spiritState: StateFlow<RoverViewState> = _spiritState.asStateFlow()

    suspend fun getSpirit() {
        roverRepository.fetchSpirit().collect { result ->
            when (result) {
                is NetworkResult.Success -> {
                    val cameraList = HashSet<String>()
                    result.data?.let { roverModel ->
                        roverModel.photos.forEach {
                            cameraList.add(it.camera.name)
                        }
                        _spiritState.update {
                            it.copy(
                                photoList = roverModel,
                                isLoading = false,
                                cameraList = cameraList
                            )
                        }
                    }
                }
                is NetworkResult.Error -> {
                    _spiritState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
                is NetworkResult.Loading -> {
                    _spiritState.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }
}