package com.example.handclash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.handclash.databinding.ActivityHomePageBinding

class home_page : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val start_btn = binding.startButton

        start_btn.setOnClickListener{
            val intent = Intent(this, game_page::class.java)
            startActivity(intent)
        }
    }
}