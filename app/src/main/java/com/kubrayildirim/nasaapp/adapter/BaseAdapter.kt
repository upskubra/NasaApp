package com.kubrayildirim.nasaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kubrayildirim.nasaapp.R
import com.kubrayildirim.nasaapp.data.model.Photo
import com.kubrayildirim.nasaapp.databinding.ItemRowBinding
import com.kubrayildirim.nasaapp.util.loadUrl

class BaseAdapter(
    private val photoList: List<Photo>,
    private val onClickListener: OnClickListener
) : ListAdapter<Photo, BaseAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photoList[position])
        holder.itemView.setOnClickListener {
            onClickListener.clickListener(photoList[position])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowBinding.bind(itemView)
        fun bind(photo: Photo) {
            photo.imgSrc.let { binding.ivCuriosity.loadUrl(it) }
            photo.id.let { binding.txtId.text = it.toString() }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

    class OnClickListener(val clickListener: (photoList: Photo) -> Unit) {
        fun onClick(photo: Photo) = clickListener(photo)
    }
}
