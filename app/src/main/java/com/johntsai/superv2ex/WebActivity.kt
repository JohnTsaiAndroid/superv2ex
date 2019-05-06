package com.johntsai.superv2ex

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar

class WebActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web)

        val webView: WebView = findViewById(R.id.webview)
        val url: String? = intent.getStringExtra("url")

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        toolbar.setNavigationOnClickListener { finish() }

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
        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                if(request?.url?.host?.contains("v2ex.com")!!){
                    return false
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        if (url != null) {
            webView.loadUrl(url)
        }
    }
}