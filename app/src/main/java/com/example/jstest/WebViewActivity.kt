package com.example.jstest

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import com.example.jstest.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
//            val url = "https://im5.lvdev.jp/app/odakyu_jssample/jssample/sample_android.html"
            val url = "file:///android_asset/first.html"
            webView.apply {
                webChromeClient = object : WebChromeClient(){}
                settings.apply {
                    javaScriptEnabled = true
                }

                addJavascriptInterface(JavaScriptInterface(), "Android")

                loadUrl(url)
            }
        }
    }

    inner class JavaScriptInterface {
        @JavascriptInterface
        fun getFromAndroid(): String {
            return "Token Value From Android Application. : Some Value"
        }
    }
}