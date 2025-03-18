package com.example.smartstock.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.smartstock.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

    }

    private fun initUI() {
        binding.nextBtn.setOnClickListener {
            val user_name:String = binding.entryName.text.toString()
            navigateToBranch(user_name) }
    }

    private fun navigateToBranch (user_name:String){
        val intent = Intent(this@MainActivity , BranchActivity::class.java)
        intent.putExtra("USER_NAME" ,user_name)
        startActivity(intent)
    }
}