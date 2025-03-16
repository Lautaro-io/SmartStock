package com.example.smartstock.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sucursales",
    foreignKeys = [
        ForeignKey(
            entity = User::class, // Tabla a la cual se relaciona
            parentColumns = ["id"], // Columna que necesita de la otra tabla
            childColumns = ["user_id"], // aca relaciona las dos tablas
            onDelete = ForeignKey.CASCADE // si se borra el usuario se eliminan sus sucursales
        )
    ],
    indices = [Index(value = ["user_id"] )]

    )
data class Sucursal(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name:String,
    val user_id : Int
)
