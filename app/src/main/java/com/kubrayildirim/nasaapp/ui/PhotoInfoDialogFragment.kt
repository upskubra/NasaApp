package com.kubrayildirim.nasaapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kubrayildirim.nasaapp.data.model.Photo
import com.kubrayildirim.nasaapp.databinding.FragmentPhotoInfoDialogBinding
import com.kubrayildirim.nasaapp.util.loadUrl

class PhotoInfoDialogFragment(
    private val photo: Photo
) :
    DialogFragment() {
    private lateinit var binding: FragmentPhotoInfoDialogBinding

    companion object {
        const val TAG = "PhotoInfoDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoInfoDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photo.imgSrc.let { binding.ivPhoto.loadUrl(it) }
        binding.txtDate.text = "Date: ${photo.earthDate}"
        binding.txtRoverName.text = "Name: ${photo.rover.name}"
        binding.txtCamName.text = "Camera: ${photo.camera.name}"
        binding.txtStatus.text = "Status: ${photo.rover.status}"
        binding.txtLaunchDate.text = "Launch Date: ${photo.rover.launchDate}"
        binding.txtLandingDate.text = "Landing Date: ${photo.rover.landingDate}"
    }
}