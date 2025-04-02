package com.example.smartstock.database.repositories

import androidx.lifecycle.LiveData
import com.example.smartstock.database.daos.SucursalDao
import com.example.smartstock.database.entities.Sucursal

class SucursalRepository(val sucursalDao: SucursalDao) {

    suspend fun insertBranch(sucursal: Sucursal) = sucursalDao.insert_branch(sucursal)

    suspend fun deleteBranch(sucursal: Sucursal) = sucursalDao.deleteBranch(sucursal)

    suspend fun sucursalCount() = sucursalDao.getSucursalCount()

    suspend fun getBranchName() = sucursalDao.getBranchName()

    suspend fun countSucursales(): Int {
        return sucursalDao.countSucursales()
    }

    suspend fun getFirstSucursal(): Sucursal? = sucursalDao.getFirstSucursal()

    fun getAllSucursales(): LiveData<List<Sucursal>> = sucursalDao.getAllSucursales()

}