package ru.ivanov23.base.adapter.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecoration(private val spaceWidth: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemPosition = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        if (itemPosition == 0) {
            outRect.left = spaceWidth
        }
        if (itemPosition < itemCount - 1) {
            outRect.right = spaceWidth
        }
        if (itemPosition == itemCount - 1) {
            outRect.right = spaceWidth
        }
    }
}