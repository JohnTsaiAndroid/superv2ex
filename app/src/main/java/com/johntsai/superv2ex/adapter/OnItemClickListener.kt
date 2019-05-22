package com.johntsai.superv2ex.adapter

import android.view.View

interface OnItemClickListener <T>{
    fun onItemClick(view: View, data: T)
}