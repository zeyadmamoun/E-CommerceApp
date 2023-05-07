package com.zeyadmaamoun.android.e_commerceapp.repository

import com.zeyadmaamoun.android.e_commerceapp.data.CartDatabase
import com.zeyadmaamoun.android.e_commerceapp.data.CartProduct
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CartProductsRepository(
    private val database: CartDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
    suspend fun addToCart(cartProduct: CartProduct){
        withContext(ioDispatcher){
            database.cartDao().addProduct(cartProduct)
        }
    }

    fun getCartProducts(): Flow<List<CartProduct>> {
        return database.cartDao().getCartProducts()
    }

    suspend fun updateProduct(cartProduct: CartProduct){
        withContext(ioDispatcher){
            database.cartDao().updateProduct(cartProduct)
        }
    }

    suspend fun deleteProduct(cartProduct: CartProduct){
        withContext(ioDispatcher){
            database.cartDao().deleteProduct(cartProduct)
        }
    }
}