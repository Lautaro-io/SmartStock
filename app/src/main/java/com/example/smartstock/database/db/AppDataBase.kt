package com.example.smartstock.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.smartstock.database.daos.ProductForBranchDao
import com.example.smartstock.database.daos.SucursalDao
import com.example.smartstock.database.entities.ProductForBranch
import com.example.smartstock.database.entities.Sucursal


@Database(
    entities = [Sucursal::class , ProductForBranch::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun sucursalDao(): SucursalDao

    abstract fun productForBranchDao(): ProductForBranchDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "supermarket"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}