package com.thor.beritahuapp.detail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thor.beritahuapp.NotConnected
import com.thor.beritahuapp.adapter.BeritaAdapter
import com.thor.beritahuapp.databinding.ActivityBeritaDetailBinding
import com.thor.beritahuapp.model.ArticlesItem

class BeritaDetail : AppCompatActivity() {
    lateinit var binding: ActivityBeritaDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeritaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (isConnect()){
            val judul = intent.getStringExtra("jdl")
            Log.d("berita detail", judul.toString())
            binding.judulDetail.text = judul.toString()


            val url = intent.getStringExtra("url").toString()
            showWebView(url)
        }else{
            binding.progressBarDetail.visibility = View.GONE
            Toast.makeText(this, "Tidak bisa terhubung dengan jaringan", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NotConnected::class.java)
            startActivity(intent)
        }






    }

    private fun showWebView(url: String?) {
        val myWebView: WebView = binding.webViewDetail
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, url)
                view?.loadUrl(url ?: "")
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding.progressBarDetail.visibility = View.VISIBLE

                super.onPageStarted(view, url, favicon)
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                binding.progressBarDetail.visibility = View.GONE
                super.onPageFinished(view, url)
            }
        }

        myWebView.loadUrl(url ?: "")
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.allowContentAccess = true
        myWebView.settings.domStorageEnabled = true
        myWebView.settings.useWideViewPort = true
        myWebView.settings.setAppCacheEnabled(true)
    }
    fun isConnect(): Boolean{
        val connect: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return  connect.activeNetworkInfo != null && connect.activeNetworkInfo!!.isConnected
    }

}