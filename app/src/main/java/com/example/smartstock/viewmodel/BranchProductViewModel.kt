package com.example.smartstock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartstock.database.entities.ProductForBranch
import com.example.smartstock.database.repositories.ProductForBranchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BranchProductViewModel @Inject constructor(
    private val repository: ProductForBranchRepository
) : ViewModel( ){

    val allProducts: LiveData<List<ProductForBranch>> = repository.getAllProduct()

    init {
        allProducts
    }

//    fun getAllProducts(){
//        viewModelScope.launch {
//            repository.getAllProduct().collect {productList ->
//                _products.value = productList
//            }
//        }
//    }

    suspend fun insertProduct(product: ProductForBranch){
        viewModelScope.launch {
            repository.insertProduct(product)
        }

    }
    fun deleteProduct(product: ProductForBranch){
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }


}