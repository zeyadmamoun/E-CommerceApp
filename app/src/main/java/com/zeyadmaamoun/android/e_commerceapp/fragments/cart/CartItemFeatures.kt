package com.zeyadmaamoun.android.e_commerceapp.fragments.cart

import com.zeyadmaamoun.android.e_commerceapp.data.CartProduct

interface CartItemFeatures {
    fun addUnit(cartProduct: CartProduct)
    fun deleteUnit(cartProduct: CartProduct)
}