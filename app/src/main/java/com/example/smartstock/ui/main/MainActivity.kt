package com.example.smartstock.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.smartstock.databinding.ActivityMainBinding
import com.example.smartstock.ui.branch.BranchActivity
import com.example.smartstock.ui.home.HomeActivity
import com.example.smartstock.viewmodel.SucursalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        checkUserData()

    }

    private fun initUI() {
        binding.nextBtn.setOnClickListener {
            val user_name: String = binding.entryName.text.toString()
            navigateToBranch(user_name)
        }
    }

    private fun navigateToBranch(user_name: String) {
        val intent = Intent(this@MainActivity, BranchActivity::class.java)
        intent.putExtra("USER_NAME", user_name)
        startActivity(intent)
    }


    //La solucion al bug que se repetia infinitamente esta funcion, fue usar una corrutina y funciones suspendidas para evitar los loop y usar una funcion aparte para arrancar el home activity
    private fun checkUserData() {
        val sucursalViewModel: SucursalViewModel by viewModels()

        lifecycleScope.launch {
            val sucursal = sucursalViewModel.getFirstSucursal()
            if (sucursal != null){
                startHomeActivity(sucursal.id, sucursal.name)
            }
        }


    }

    private fun startHomeActivity(id:Int , name :String){
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra("SUC_ID", id)
            putExtra("SUC_NAME",name)
        }
        startActivity(intent)
        finishAffinity()
    }
}