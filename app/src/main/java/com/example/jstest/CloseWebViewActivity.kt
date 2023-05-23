package com.example.jstest

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jstest.databinding.ActivityWebviewBinding

class CloseWebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            val url = "file:///android_asset/second.html"
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
        fun runNativeCodeToCloseWebView() {
            Toast.makeText(applicationContext, "Close WebView!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}