package com.example.smartstock.ui.branch

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.smartstock.database.entities.Sucursal
import com.example.smartstock.databinding.ActivityBranchBinding
import com.example.smartstock.ui.home.HomeActivity
import com.example.smartstock.viewmodel.SucursalViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        val user_name: String = intent.getStringExtra("USER_NAME")!!
        binding.tvGreeting.text = "Hola $user_name!"
        binding.addProfile.setOnClickListener {
            val suc_name = binding.sucName.text.toString().trim()
            if (suc_name.isNotEmpty()) {
                val sucursal = Sucursal(1, suc_name, user_name)
                lifecycleScope.launch {
                    sucursalViewModel.insertBranch(sucursal)
                    navigateToHome(sucursal)
                }
            }
        }
    }

    private fun navigateToHome(sucursal: Sucursal){
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("SUC_NAME", sucursal.name)
        intent.putExtra("SUC_ID",sucursal.id )
        intent.putExtra("USER_NAME", sucursal.user_name)
        startActivity(intent)

    }
}