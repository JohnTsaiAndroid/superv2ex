package com.johntsai.superv2ex

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }
}