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

class DomainConstraintsWebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            val url = "https://google.com"
            webView.apply {
                webViewClient = object : WebViewClient(){
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        request?.url?.host?.let {
                            if (isDomainAllowed(it)) {
                                // 許可してるDomain何もしない
                                return false
                            } else {
                                // 制約掛けてない場合の処理
                                // ここでは外部WebViewで開く
//                                request.url?.let {uri ->
//                                    val intent = Intent(Intent.ACTION_VIEW, uri)
//                                    startActivity(intent)
//                                }
                                // ここではToastで表示
                                Toast.makeText(applicationContext, "Domain is Not Allowed", Toast.LENGTH_SHORT).show()

                                return true
                            }
                        }

                        return super.shouldOverrideUrlLoading(view, request)
                    }
                }
                settings.apply {
                    javaScriptEnabled = true
                }

                loadUrl(url)
            }
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