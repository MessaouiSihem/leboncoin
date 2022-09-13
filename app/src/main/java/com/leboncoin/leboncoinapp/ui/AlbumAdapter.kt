package com.leboncoin.leboncoinapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.domain.models.Album
import com.leboncoin.leboncoinapp.databinding.AlbumItemLayoutBinding
import com.leboncoin.leboncoinapp.extension.loadImage

class AlbumAdapter : ListAdapter<Album, AlbumAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            AlbumItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(
        private val itemLayoutBinding: AlbumItemLayoutBinding,
    ) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {
        fun bind(album: Album) {
            itemLayoutBinding.albumTitle.text = album.title
            itemLayoutBinding.albumImage.scaleType = ImageView.ScaleType.FIT_XY
            itemLayoutBinding.albumImage.loadImage(album.thumbnailUrl)

        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}