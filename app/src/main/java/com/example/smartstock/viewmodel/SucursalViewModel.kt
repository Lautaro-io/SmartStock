package com.example.smartstock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartstock.database.entities.Sucursal
import com.example.smartstock.database.repositories.SucursalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class SucursalViewModel @Inject constructor(
    private val repository: SucursalRepository
) : ViewModel() {

    fun insertBranch(sucursal: Sucursal) {
        viewModelScope.launch {
            repository.insertBranch(sucursal)
        }
    }
    fun deleteBranch(sucursal: Sucursal){
        viewModelScope.launch { repository.deleteBranch(sucursal) }
    }
}
