package com.example.smartstock.database.remote

import com.google.gson.annotations.SerializedName

data class ProductDataResponse(

    @SerializedName ("product") val product : DataProduct?
)

data class DataProduct (
    @SerializedName("image_url") val image:String? = null,
    @SerializedName("product_name") val name: String? = "introduce el nombre."
)