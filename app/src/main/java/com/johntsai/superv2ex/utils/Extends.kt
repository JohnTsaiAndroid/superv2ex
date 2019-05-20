package com.johntsai.superv2ex.utils

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.AdapterView
import android.widget.TextView

fun TextView.setHtml(html: String){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        this.text = Html.fromHtml(html)
    }
}
open class OnItemSelectedListener: AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }
}