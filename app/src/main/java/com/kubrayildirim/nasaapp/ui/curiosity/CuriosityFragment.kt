package com.kubrayildirim.nasaapp.ui.curiosity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kubrayildirim.nasaapp.adapter.BaseAdapter
import com.kubrayildirim.nasaapp.data.model.Photo
import com.kubrayildirim.nasaapp.databinding.FragmentCuriosityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CuriosityFragment : Fragment() {
    private lateinit var binding: FragmentCuriosityBinding
    private val curiosityViewModel: CuriosityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCuriosityBinding.inflate(inflater)
        updateCuriosityState()
        return binding.root
    }

    private fun updateCuriosityState() {
        viewLifecycleOwner.lifecycleScope.launch {
            curiosityViewModel.getCuriosity()
            curiosityViewModel.curiosityState.collect {
                if (it.isLoading) {
                    binding.progressCuriosity.visibility = View.VISIBLE
                    binding.rvCuriosity.visibility = View.GONE
                } else if (it.photoList?.photos?.isNotEmpty() == true) {
                    binding.progressCuriosity.visibility = View.GONE
                    initRecycler(it.photoList.photos)
                } else {
                    Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initRecycler(list: List<Photo>) {
        binding.rvCuriosity.apply {
            adapter = BaseAdapter(list)
        }

    }
}