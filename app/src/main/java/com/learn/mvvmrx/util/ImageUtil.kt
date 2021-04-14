package com.learn.mvvmrx.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.learn.mvvmrx.model.Datum


class ImageUtil {
companion object {
    @BindingAdapter("profileImage")
    @JvmStatic
    fun loadImage(view: ImageView, imgurl: String) {
        Glide.with(view.context)
                .load(imgurl).apply(RequestOptions().circleCrop())
                .into(view)
    }
}

}