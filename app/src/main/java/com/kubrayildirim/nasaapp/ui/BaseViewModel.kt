package com.kubrayildirim.nasaapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        // make job after turn main thread
        get() = job + Dispatchers.Main

    // if app close, job cancels
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}