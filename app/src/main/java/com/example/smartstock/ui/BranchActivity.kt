package com.example.smartstock.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.smartstock.R
import com.example.smartstock.databinding.ActivityBranchBinding

class BranchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBranchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBranchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        val user_name:String = intent.getStringExtra("USER_NAME").toString()
        binding.tvGreeting.text = "Hola! , ${user_name}"
    }
}