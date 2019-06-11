package com.johntsai.superv2ex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.johntsai.superv2ex.BR
import com.johntsai.superv2ex.R
import com.johntsai.superv2ex.data.Topic

class TopicAdapter: ListAdapter<Topic, TopicAdapter.ViewHolder>(TopicDiffCallback()), View.OnClickListener {

    private var listener: OnItemClickListener<Topic>? = null

    override fun onClick(v: View?) {
        if(listener != null) {
            listener!!.onItemClick(v!!, v.tag as Topic)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener<Topic>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewDataBinding
                = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.layout_topic, parent, false)
        val holder = ViewHolder(viewDataBinding)
        holder.itemView.setOnClickListener(this)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder(dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {

        private val viewDataBinding = dataBinding

        fun bind(topic: Topic){
            itemView.tag = topic
            viewDataBinding.setVariable(BR.topic, topic)
            viewDataBinding.executePendingBindings()

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