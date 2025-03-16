package com.example.smartstock.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity (
    tableName = "product_for_branch",
    foreignKeys = [
        ForeignKey(
            entity = Sucursal::class,
            parentColumns = ["id"],
            childColumns = ["suc_id"]
        )
    ]

)
data class ProductForBranch(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0 ,
    val name_product:String,
    val stock: Int,
    val expire_date :String,
    val suc_id :Int
)
