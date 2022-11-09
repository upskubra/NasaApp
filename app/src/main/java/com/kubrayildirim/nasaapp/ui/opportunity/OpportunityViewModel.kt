package com.kubrayildirim.nasaapp.ui.opportunity

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
class OpportunityViewModel @Inject constructor(
    private val roverRepository: RoverRepository,
    application: Application
) : BaseViewModel(application) {
    private val _opportunityState = MutableStateFlow(RoverViewState())
    val opportunityState: StateFlow<RoverViewState> = _opportunityState.asStateFlow()

    suspend fun getOpportunity() {
        roverRepository.fetchOpportunity().collect { result ->
            when (result) {
                is NetworkResult.Success -> {
                    _opportunityState.value =
                        result.data?.let {
                            RoverViewState(
                                photoList = it,
                                isLoading = false
                            )
                        }!!
                }
                is NetworkResult.Error -> {
                    _opportunityState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                }
                is NetworkResult.Loading -> {
                    _opportunityState.update {
                        it.copy(isLoading = true)
                    }
                }
            }
        }
    }
}