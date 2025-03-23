package com.example.smartstock.database.repositories

import com.example.smartstock.database.daos.SucursalDao
import com.example.smartstock.database.entities.Sucursal

class SucursalRepository( val sucursalDao: SucursalDao) {

    suspend fun insertBranch(sucursal: Sucursal) = sucursalDao.insert_branch(sucursal)

    suspend fun deleteBranch(sucursal: Sucursal) = sucursalDao.deleteBranch(sucursal)

}