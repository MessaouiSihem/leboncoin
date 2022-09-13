package com.leboncoin.leboncoinapp.ui

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.leboncoinapp.R

class AlbumItemDecoration(context: Context): RecyclerView.ItemDecoration() {

    private val margin: Int = context.resources.getDimensionPixelSize(R.dimen.recycler_view_item_margin)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            left = margin
            right = 0
            bottom = 0
            top = margin
        }
    }

}