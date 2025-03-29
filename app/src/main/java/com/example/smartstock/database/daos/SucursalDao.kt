package com.example.smartstock.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.smartstock.database.entities.Sucursal


@Dao
interface SucursalDao {


    @Insert
    suspend fun insert_branch(sucursal: Sucursal) : Long

    @Delete
    suspend fun deleteBranch(sucursal: Sucursal)

    @Query ("SELECT COUNT(*) FROM sucursales")
    suspend fun getSucursalCount(): Int

    @Query ("SELECT name FROM SUCURSALES")
    suspend fun getBranchName():String

    @Query("SELECT count(*) FROM SUCURSALES")
    suspend fun countSucursales(): Int

    @Query("SELECT * FROM SUCURSALES LIMIT 1")
    suspend fun getFirstSucursal(): Sucursal?

}