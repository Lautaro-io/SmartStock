package com.example.smartstock.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.smartstock.database.entities.ProductForBranch
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductForBranchDao {

    @Insert
    suspend fun insertProductForBranch(product: ProductForBranch)

    @Query("SELECT * FROM product_for_branch ")
    fun getAllProductForBranch() : Flow<List<ProductForBranch>>

    @Delete
    suspend fun deleteProduct(productForBranch: ProductForBranch)
}