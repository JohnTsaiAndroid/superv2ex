package com.johntsai.superv2ex.ui

import com.johntsai.superv2ex.api.RetrofitInstance

class LatestTopicFragment : BaseTopicFragment() {

    override fun getCall() {
        call = RetrofitInstance.get().service.latestTopic()
    }

}