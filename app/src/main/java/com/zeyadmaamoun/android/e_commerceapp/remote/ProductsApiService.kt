package com.zeyadmaamoun.android.e_commerceapp.remote

import com.zeyadmaamoun.android.e_commerceapp.models.Product

interface ProductsApiService {
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(productId: Int): Product?
}