package com.example.smartstock.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.smartstock.database.entities.Sucursal


@Dao
interface SucursalDao {


    @Insert
    suspend fun insert_branch(sucursal: Sucursal)

    @Delete
    suspend fun delete_branch()

}