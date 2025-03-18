package com.example.smartstock.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.smartstock.database.entities.ProductForBranch

@Dao
interface ProductForBranchDao {

    @Insert
    suspend fun insertProductForBranch(product: ProductForBranch)

    @Query("SELECT * FROM product_for_branch WHERE ID =  :branch_id")
    suspend fun getAllProductForBranch(branch_id : Int)

    @Delete
    suspend fun deleteProduct()
}