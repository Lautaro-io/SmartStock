package com.example.smartstock.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")

data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val product_name: String,
    val expirate: String
)
