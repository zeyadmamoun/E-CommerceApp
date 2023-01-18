package com.zeyadmaamoun.android.e_commerceapp.models

import kotlinx.serialization.Serializable


@Serializable
data class Product(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)
