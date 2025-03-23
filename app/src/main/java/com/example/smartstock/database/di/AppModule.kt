package com.example.smartstock.database.di

import android.app.Application
import androidx.room.Room
import com.example.smartstock.database.daos.ProductForBranchDao
import com.example.smartstock.database.daos.SucursalDao
import com.example.smartstock.database.db.AppDataBase
import com.example.smartstock.database.repositories.ProductForBranchRepository
import com.example.smartstock.database.repositories.SucursalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    @Singleton
    fun providesDataBase(app: Application): AppDataBase {
        return Room.databaseBuilder(app, AppDataBase::class.java, "supermarket_db").build()
    }
    @Provides
    fun providesProductDao(appDataBase: AppDataBase) : ProductForBranchDao{
        return appDataBase.productForBranchDao()
    }
    @Provides
    fun providesSucursalDao(appDataBase: AppDataBase) : SucursalDao{
        return appDataBase.sucursalDao()
    }

    @Provides
    fun providesSucursalRepository(dao: SucursalDao): SucursalRepository{
        return SucursalRepository(dao)
    }

    @Provides
    fun providesBranchRepository(dao: ProductForBranchDao): ProductForBranchRepository{
        return ProductForBranchRepository(dao)
    }



}