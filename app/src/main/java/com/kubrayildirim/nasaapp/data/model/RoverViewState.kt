package com.kubrayildirim.nasaapp.data.model

data class RoverViewState(
    val isLoading: Boolean = true,
    val photoList: RoverModel? = null,
    val error: String = ""
)

