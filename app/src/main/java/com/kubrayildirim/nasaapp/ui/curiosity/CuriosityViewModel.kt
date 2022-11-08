package com.kubrayildirim.nasaapp.ui.curiosity

import android.app.Application
import com.kubrayildirim.nasaapp.data.remote.NetworkResult
import com.kubrayildirim.nasaapp.data.repository.RoverRepository
import com.kubrayildirim.nasaapp.ui.BaseViewModel
import com.kubrayildirim.nasaapp.data.model.RoverViewState
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
                    _curiosityState.value =
                        result.data?.let {
                            RoverViewState(
                                photoList = it,
                                isLoading = false
                            )
                        }!!
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
