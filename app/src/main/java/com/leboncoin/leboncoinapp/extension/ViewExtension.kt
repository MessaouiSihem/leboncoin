package com.leboncoin.leboncoinapp.extension

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

fun AppCompatImageView.loadImage(url: String?) {
    // Load image only when url is not null or empty
    // Header with User-Agent key is needed to show image
    if (!url.isNullOrEmpty()) {
        val glideUrl = GlideUrl(
            url,
            LazyHeaders.Builder()
                .addHeader("User-Agent", "1")
                .build()
        )
        Glide.with(context)
            .load(glideUrl)
            .into(this)
    }
}