package com.example.jstest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            btnInject.setOnClickListener {
                startActivity(Intent(applicationContext, WebViewActivity::class.java))
            }

            btnClose.setOnClickListener {
                startActivity(Intent(applicationContext, CloseWebViewActivity::class.java))
            }
        }
    }
}