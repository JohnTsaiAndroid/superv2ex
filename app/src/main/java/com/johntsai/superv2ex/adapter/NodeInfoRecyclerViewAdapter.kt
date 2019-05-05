package com.johntsai.superv2ex.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.johntsai.superv2ex.R
import com.johntsai.superv2ex.data.NodeData
import java.util.*

class NodeInfoRecyclerViewAdapter(val context: Context, val dataList: List<NodeData>) : RecyclerView.Adapter<NodeInfoRecyclerViewAdapter.ViewHolder>(), View.OnClickListener {

    private var listener: OnItemClickListener? = null

    override fun onClick(v: View?) {
        if (listener != null) {
            listener!!.onItemClick(v!!, v.tag as Int)
        }
    }

    public fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    private val random: Random = Random()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_node, parent, false))
        viewHolder.itemView.setOnClickListener(this)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.text.text = data.name
        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)
        holder.itemView.tag = position
        holder.itemView.setBackgroundColor(Color.rgb(r, g, b))
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.node_text)
    }
}