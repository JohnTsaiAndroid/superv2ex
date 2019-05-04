package com.johntsai.superv2ex.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.johntsai.superv2ex.R
import com.johntsai.superv2ex.data.Topic


class TopicRecyclerViewAdapter(val context: Context, val dataList: List<Topic>) : RecyclerView.Adapter<TopicRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_topic, parent, false))
        holder.view.setOnClickListener { v -> }
        return holder
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.textView.text = Html.fromHtml(data.title, Html.FROM_HTML_MODE_COMPACT)
        } else {
            holder.textView.text = Html.fromHtml(data.title)
        }

        Glide.with(context).load("http:" + data.member.avatarNormal).into(holder.imageView)

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.topic_content)
        var imageView: ImageView = view.findViewById(R.id.node_avatar)
        val view: View = view
    }
}