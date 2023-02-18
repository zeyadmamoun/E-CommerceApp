package com.zeyadmaamoun.android.e_commerceapp.models

data class CartProduct(
    val id: Int,
    val name: String,
    val image: String,
    val price: Double,
    var units: Int = 1
)
