package com.johntsai.superv2ex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.johntsai.superv2ex.R
import com.johntsai.superv2ex.data.Topic
import com.johntsai.superv2ex.utils.addHttps
import com.johntsai.superv2ex.utils.setHtml

class TopicAdapter: ListAdapter<Topic, TopicAdapter.ViewHolder>(TopicDiffCallback()), View.OnClickListener {

    private var listener: OnItemClickListener<Topic>? = null

    override fun onClick(v: View?) {
        if(listener != null) {
            listener!!.onItemClick(v!!, v.tag as Topic)
        }
    }

    public fun setOnItemClickListener(listener: OnItemClickListener<Topic>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_topic, parent, false))
        holder.itemView.setOnClickListener(this)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var textView: TextView = view.findViewById(R.id.topic_content)
        var imageView: ImageView = view.findViewById(R.id.node_avatar)
        var lastReplyTextView: TextView = view.findViewById(R.id.last_reply_text)

        fun bind(topic: Topic){
            itemView.tag = topic
            textView.setHtml(topic.title)
            Glide.with(itemView.context)
                    .load(topic.member.avatarNormal.addHttps())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView)
            lastReplyTextView.text = "最后回复来自:${topic.lastReplyBy}"

        }
    }
}

private class TopicDiffCallback: DiffUtil.ItemCallback<Topic>() {
    override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean {
        return oldItem == newItem
    }

}