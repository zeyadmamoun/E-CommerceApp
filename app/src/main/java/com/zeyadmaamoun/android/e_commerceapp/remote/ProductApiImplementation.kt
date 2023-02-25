package com.zeyadmaamoun.android.e_commerceapp.remote

import android.util.Log
import com.zeyadmaamoun.android.e_commerceapp.models.Product
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ProductApiImplementation(private var client: HttpClient) : ProductsApiService {
    override suspend fun getProducts(): List<Product>? {
        return try {
            client.get("https://fakestoreapi.com/products").body()
        } catch (e: Exception) {
            Log.i("Ktor Client", e.toString())
            null
        }
    }
    override suspend fun getProductById(productId: Int): Product? {
        return try {
            client.get("https://fakestoreapi.com/products/$productId").body()
        } catch (e: Exception) {
            Log.i("Ktor Client", e.toString())
            null
        }
    }
}