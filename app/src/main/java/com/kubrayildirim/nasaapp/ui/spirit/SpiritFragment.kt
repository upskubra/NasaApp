package com.kubrayildirim.nasaapp.ui.spirit

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
import com.kubrayildirim.nasaapp.databinding.FragmentSpiritBinding
import com.kubrayildirim.nasaapp.ui.PhotoInfoDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SpiritFragment : Fragment() {
    private lateinit var binding: FragmentSpiritBinding
    private val spiritViewModel: SpiritViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpiritBinding.inflate(inflater)
        updateSpiritState()
        return binding.root
    }

    private fun updateSpiritState() {
        viewLifecycleOwner.lifecycleScope.launch {
            spiritViewModel.getSpirit()
            spiritViewModel.spiritState.collect {
                if (it.isLoading) {
                    binding.progressSpirit.visibility = View.VISIBLE
                    binding.rvSpirit.visibility = View.GONE
                } else if (it.photoList?.photos?.isNotEmpty() == true) {
                    binding.progressSpirit.visibility = View.GONE
                    initRecycler(it.photoList.photos)
                } else {
                    Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initRecycler(list: List<Photo>) {
        binding.rvSpirit.apply {
            adapter = BaseAdapter(list, BaseAdapter.OnClickListener {
                PhotoInfoDialogFragment(it).show(childFragmentManager, PhotoInfoDialogFragment.TAG)
            })
        }
    }
}