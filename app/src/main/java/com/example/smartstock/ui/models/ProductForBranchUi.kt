package com.example.smartstock.ui.models

data class ProductForBranchUi(
    val id : Int,
    val name : String,
    val count : Int ,
    val branchName : String,
    val expireDate : String,
    val imgUrl : String? = null
)
