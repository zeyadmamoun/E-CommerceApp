package com.zeyadmaamoun.android.e_commerceapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val count: Int,
    val rate: Float
)
