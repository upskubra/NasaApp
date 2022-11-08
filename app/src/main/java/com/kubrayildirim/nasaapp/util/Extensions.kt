package com.kubrayildirim.nasaapp.util

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .into(this)
}