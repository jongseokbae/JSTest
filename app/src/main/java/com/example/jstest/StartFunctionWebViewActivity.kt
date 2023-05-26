package com.example.jstest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jstest.databinding.ActivityWebviewBinding
import java.util.*


class StartFunctionWebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
//            val url = "https://google.com"
//            val url = "file:///android_asset/third.html"
            val url = "https://im5.lvdev.jp/app/odakyu_jssample/jssample/sample_android.html"
            webView.apply {
                webViewClient = object : WebViewClient(){}
                settings.apply {
                    javaScriptEnabled = true
                    javaScriptCanOpenWindowsAutomatically = true
                }

                addJavascriptInterface(JavaScriptInterface(), "Android")

                loadUrl(url)
            }
        }
    }

    inner class JavaScriptInterface {
        @JavascriptInterface
        fun runNativeCodeToCloseWebView() {
            Log.e("BAE","runNativeCodeToCloseWebView")
            Toast.makeText(applicationContext, "This is Started WebView With Native Function.", Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun getFromAndroid(): String {
            return "Token Value From Android Application. : Some Value"
        }
    }

    private fun isDomainAllowed(domain: String): Boolean {
        val allowedDomains = listOf("one-odakyu.com")
        return allowedDomains.contains(domain)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webView.canGoBack()) {
            binding.webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}