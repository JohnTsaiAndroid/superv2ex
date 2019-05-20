package com.johntsai.superv2ex.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.johntsai.superv2ex.R
import com.johntsai.superv2ex.api.RetrofitInstance

class HotTopicFragment : BaseTopicFragment() {

    override fun getCall() {
        call = RetrofitInstance.get().service.hotTopic()
    }

    override fun onItemClick(view: View, position: Int) {
        val bundle = Bundle()
        val data = datas[position]
        bundle.putString("url", data.url)
        findNavController().navigate(R.id.action_hotTopicFragment_to_webActivity, bundle, options)
    }
}