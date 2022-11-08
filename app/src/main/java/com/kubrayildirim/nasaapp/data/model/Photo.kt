package com.kubrayildirim.nasaapp.data.model


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("camera")
    val camera: Camera,
    @SerializedName("earth_date")
    val earthDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img_src")
    val imgSrc: String,
    @SerializedName("rover")
    val rover: Rover,
    @SerializedName("sol")
    val sol: Int
)