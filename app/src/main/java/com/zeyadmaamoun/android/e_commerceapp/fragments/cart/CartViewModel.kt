package com.zeyadmaamoun.android.e_commerceapp.fragments.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zeyadmaamoun.android.e_commerceapp.data.CartProduct
import com.zeyadmaamoun.android.e_commerceapp.remote.ProductsApiService
import com.zeyadmaamoun.android.e_commerceapp.repository.CartProductsRepository
import kotlinx.coroutines.launch


class CartViewModel(
    private val service: ProductsApiService,
    private val repository: CartProductsRepository
) : ViewModel() , CartItemFeatures {

    val cartProds: LiveData<List<CartProduct>> = repository.getCartProducts().asLiveData()

    private fun updateProduct(cartProduct: CartProduct){
        viewModelScope.launch {
            repository.updateProduct(cartProduct)
        }
    }

    private fun deleteProduct(cartProduct: CartProduct){
        viewModelScope.launch {
            repository.deleteProduct(cartProduct)
        }
    }

    // coming functions are implementation to CartItemFeatures interface that we will send
    // to CartProductsAdapter to handle all click events.
    override fun addUnit(cartProduct: CartProduct) {
        cartProduct.units = cartProduct.units.plus(1)
        updateProduct(cartProduct)
    }

    override fun deleteUnit(cartProduct: CartProduct) {
        if (cartProduct.units <= 1){
            deleteProduct(cartProduct)
        }else{
            cartProduct.units = cartProduct.units.minus(1)
            updateProduct(cartProduct)
        }
    }
}