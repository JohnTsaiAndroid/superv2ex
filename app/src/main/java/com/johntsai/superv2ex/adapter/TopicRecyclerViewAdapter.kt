package com.johntsai.superv2ex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.johntsai.superv2ex.R
import com.johntsai.superv2ex.data.Topic
import com.johntsai.superv2ex.utils.setHtml


class TopicRecyclerViewAdapter(val context: Context, val dataList: List<Topic>) : RecyclerView.Adapter<TopicRecyclerViewAdapter.ViewHolder>(), View.OnClickListener {

    private var listener: OnItemClickListener? = null

    override fun onClick(v: View?) {
        if(listener != null) {
            listener!!.onItemClick(v!!, v.getTag() as Int)
        }
    }

    public fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_topic, parent, false))
        holder.itemView.setOnClickListener(this)
        return holder
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.textView.setHtml(data.title)
        Glide.with(context).load("http:" + data.member.avatarNormal).into(holder.imageView)
        holder.lastReplyTextView.text = "最后回复来自:${data.lastReplyBy}"
        holder.itemView.tag = position
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView = view.findViewById(R.id.topic_content)
        var imageView: ImageView = view.findViewById(R.id.node_avatar)
        var lastReplyTextView: TextView = view.findViewById(R.id.last_reply_text)
    }

    interface OnItemClickListener {
        fun onItemClick(view:View, position:Int);
    }
}