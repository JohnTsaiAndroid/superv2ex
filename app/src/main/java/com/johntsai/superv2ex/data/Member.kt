package com.johntsai.superv2ex.data

/**
 * "member": {
"username": "darouwan",
"website": "darouwan.org",
"github": "",
"psn": "",
"avatar_normal": "//cdn.v2ex.com/avatar/d5b5/d916/105232_mini.png?m=1437958379",
"bio": "",
"url": "https://www.v2ex.com/u/darouwan",
"tagline": "",
"twitter": "",
"created": 1426643391,
"avatar_large": "//cdn.v2ex.com/avatar/d5b5/d916/105232_mini.png?m=1437958379",
"avatar_mini": "//cdn.v2ex.com/avatar/d5b5/d916/105232_mini.png?m=1437958379",
"location": "",
"btc": "",
"id": 105232
},
 *
 */

data class Member(
        val username: String,
        val website: String,
        val github: String,
        val psn: String,
        val avatarNormal:String,
        val bio:String,
        val id:Int
)