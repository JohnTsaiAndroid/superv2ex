package com.johntsai.superv2ex

import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web)

        val webView = findViewById<WebView>(R.id.webview)
        val url: String? = intent.getStringExtra("url");

        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.defaultTextEncodingName = "utf-8"
        settings.loadsImagesAutomatically = true
        settings.allowFileAccess = true
        val path = filesDir.absolutePath + "cache/"
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.setAppCachePath(path)
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        if(url != null) {
            webView!!.loadUrl(url)
        }
    }
}