package com.johntsai.superv2ex.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.johntsai.superv2ex.R
import com.johntsai.superv2ex.adapter.TopicRecyclerViewAdapter
import com.johntsai.superv2ex.api.RetrofitInstance
import com.johntsai.superv2ex.data.Topic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotTopicFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TopicRecyclerViewAdapter
    val datas: MutableList<Topic> = ArrayList()
    lateinit var call: Call<List<Topic>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hot_topic, container, false)
        recyclerView = view.findViewById(R.id.hot_topic_recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = TopicRecyclerViewAdapter(view.context,datas)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.setHasFixedSize(true)


        call = RetrofitInstance.get().service.hotTopic()

        Log.d("cj", "onViewCreated")

        call.enqueue(object : Callback<List<Topic>>{
            override fun onFailure(call: Call<List<Topic>>, t: Throwable) {
            }

            override fun onResponse(result: Call<List<Topic>>, response: Response<List<Topic>>) {
                val topicList = response.body();
                if (topicList != null) {
                    datas.addAll(topicList)
                    adapter.notifyDataSetChanged()
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        call.cancel()
    }



}