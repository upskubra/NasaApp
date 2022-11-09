package com.kubrayildirim.nasaapp.ui.opportunity

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
import com.kubrayildirim.nasaapp.databinding.FragmentOpportunityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OpportunityFragment : Fragment() {
    private lateinit var binding: FragmentOpportunityBinding
    private val opportunityViewModel: OpportunityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOpportunityBinding.inflate(inflater)
        updateOpportunityState()
        return binding.root
    }

    private fun updateOpportunityState() {
        viewLifecycleOwner.lifecycleScope.launch {
            opportunityViewModel.getOpportunity()
            opportunityViewModel.opportunityState.collect {
                if (it.isLoading) {
                    binding.progressOpportunity.visibility = View.VISIBLE
                    binding.rvOpportunity.visibility = View.GONE
                } else if (it.photoList?.photos?.isNotEmpty() == true) {
                    binding.progressOpportunity.visibility = View.GONE
                    initRecycler(it.photoList.photos)
                } else {
                    Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initRecycler(list: List<Photo>) {
        binding.rvOpportunity.apply {
            adapter = BaseAdapter(list)
        }

    }
}