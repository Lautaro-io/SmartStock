package com.example.smartstock.database.repositories

import com.example.smartstock.database.daos.ProductForBranchDao
import com.example.smartstock.database.entities.ProductForBranch


class ProductForBranchRepository (private val productForBranchDao: ProductForBranchDao) {

    fun getAllProduct() = productForBranchDao.getAllProductForBranch()

    suspend fun insertProduct(productForBranch: ProductForBranch) = productForBranchDao.insertProductForBranch(productForBranch)
}