package com.johntsai.superv2ex.data

/**
 * "node": {
"avatar_large": "//cdn.v2ex.com/navatar/4ea0/6fbc/770_large.png?m=1553247082",
"name": "career",
"avatar_normal": "//cdn.v2ex.com/navatar/4ea0/6fbc/770_normal.png?m=1553247082",
"title": "职场话题",
"url": "https://www.v2ex.com/go/career",
"topics": 6216,
"footer": "",
"header": "这里，我们聊聊那些工作中遇到的开心和不开心的事。",
"title_alternative": "Career",
"avatar_mini": "//cdn.v2ex.com/navatar/4ea0/6fbc/770_mini.png?m=1553247082",
"stars": 1192,
"root": false,
"id": 770,
"parent_node_name": "work"
}
 */
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