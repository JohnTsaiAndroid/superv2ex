package com.johntsai.superv2ex.data

data class Node(
        val avatarLarge: String,
        val name: String,
        val avatarNormal: String,
        val title: String,
        val url: String,
        val topics: Int,
        val footer: String,
        val header: String,
        val titleAlternative: String,
        val avatarMini: String,
        val stars: Int,
        val id: Int,
        val parentNodeName: String
)