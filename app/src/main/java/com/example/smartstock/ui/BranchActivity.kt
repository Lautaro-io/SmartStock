package com.example.smartstock.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.smartstock.database.entities.Sucursal
import com.example.smartstock.databinding.ActivityBranchBinding
import com.example.smartstock.viewmodel.SucursalViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BranchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBranchBinding
    private val sucursalViewModel: SucursalViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBranchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }


    private fun initUI() {
        val user_name: String = intent.getStringExtra("USER_NAME").toString()
        binding.tvGreeting.text = "Hola ${user_name}!"
        binding.addProfile.setOnClickListener {
            val suc_name = binding.sucName.text.toString().trim()
            if (suc_name.isNotEmpty()) {
                sucursalViewModel.insertBranch(Sucursal(0, suc_name, user_name))
                Log.i("CHELO", "Usuario ${user_name} Sucursal ${suc_name}")
            }
        }
    }
}


