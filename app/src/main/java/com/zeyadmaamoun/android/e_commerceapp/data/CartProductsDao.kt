package com.zeyadmaamoun.android.e_commerceapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartProductsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(cartProduct: CartProduct)

    @Update
    suspend fun updateProduct(cartProduct: CartProduct)

    @Delete
    suspend fun deleteProduct(cartProduct: CartProduct)

    @Query("SELECT * FROM cart_products")
    fun getCartProducts(): Flow<List<CartProduct>>
}