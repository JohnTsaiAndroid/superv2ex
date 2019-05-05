package com.johntsai.superv2ex.ui

import com.johntsai.superv2ex.api.RetrofitInstance

class HotTopicFragment : BaseTopicFragment() {

    override fun getCall() {
        call = RetrofitInstance.get().service.hotTopic()
    }
}