package com.example.smartstock.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "sucursales"
)
data class Sucursal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val user_name: String
)
