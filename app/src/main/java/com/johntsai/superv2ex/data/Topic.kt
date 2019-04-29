package com.johntsai.superv2ex.data

data class Topic(
        val lastReplyBy:String,
        val node:Node,
        val member:Member,
        val url: String
)