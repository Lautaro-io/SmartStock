package com.example.smartstock.database.repositories

import androidx.lifecycle.LiveData
import com.example.smartstock.database.daos.ProductForBranchDao
import com.example.smartstock.database.entities.ProductForBranch


class ProductForBranchRepository (private val productForBranchDao: ProductForBranchDao) {

    fun getAllProduct(): LiveData<List<ProductForBranch>> = productForBranchDao.getAllProductForBranch()

    suspend fun insertProduct(productForBranch: ProductForBranch){
        productForBranchDao.insertProductForBranch(productForBranch)

    }

    suspend fun deleteProduct(productForBranch: ProductForBranch) = productForBranchDao.deleteProduct(productForBranch)
}