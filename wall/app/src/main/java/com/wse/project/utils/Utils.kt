package com.wse.project.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

object  Utils {
    fun View.visible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun ImageView.loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}