package com.johntsai.superv2ex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.johntsai.superv2ex.R
import com.johntsai.superv2ex.adapter.OnItemClickListener
import com.johntsai.superv2ex.adapter.TopicAdapter
import com.johntsai.superv2ex.data.Topic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseTopicFragment : Fragment() , OnItemClickListener <Topic> {

    override fun onItemClick(view: View, data: Topic) {}
    abstract fun getCall()

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TopicAdapter
    lateinit var call: Call<List<Topic>>

    val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hot_topic, container, false)
        recyclerView = view.findViewById(R.id.hot_topic_recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = TopicAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.setHasFixedSize(true)
        adapter.setOnItemClickListener(this)

        getCall()
        call.enqueue(object : Callback<List<Topic>> {
            override fun onFailure(call: Call<List<Topic>>, t: Throwable) {
            }

            override fun onResponse(result: Call<List<Topic>>, response: Response<List<Topic>>) {
                val topicList = response.body();
                if (topicList != null) {
                    adapter.submitList(topicList)
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        call.cancel()
    }


}