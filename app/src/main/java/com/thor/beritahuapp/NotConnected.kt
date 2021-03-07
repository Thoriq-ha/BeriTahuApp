package com.thor.beritahuapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thor.beritahuapp.databinding.ActivityMainBinding
import com.thor.beritahuapp.databinding.NotConnectedBinding
import com.thor.beritahuapp.detail.BeritaDetail

class NotConnected : AppCompatActivity() {
    lateinit var binding: NotConnectedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NotConnectedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNoInternet.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}