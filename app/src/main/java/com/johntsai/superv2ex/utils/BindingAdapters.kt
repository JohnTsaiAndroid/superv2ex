package com.johntsai.superv2ex.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("app:imagesrc")
fun setImageSrc(view: ImageView, url: String) {
    Glide.with(view.context)
            .load(url.addHttps())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
}

@BindingAdapter("app:html")
fun setTextViewHtml(view: TextView, html: String) {
    view.setHtml(html)
}