package com.zeyadmaamoun.android.e_commerceapp.fragments.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeyadmaamoun.android.e_commerceapp.data.CartProduct
import com.zeyadmaamoun.android.e_commerceapp.fragments.home.LoadingStatus
import com.zeyadmaamoun.android.e_commerceapp.models.Product
import com.zeyadmaamoun.android.e_commerceapp.remote.ProductsApiService
import com.zeyadmaamoun.android.e_commerceapp.repository.CartProductsRepository
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val service: ProductsApiService,                    // so we can call getItemById()
    private val repository: CartProductsRepository              // so we can add the product to cart
) : ViewModel() {

    private val _status = MutableLiveData<LoadingStatus>()
    val status: LiveData<LoadingStatus> = _status

    private var _productInDetails = MutableLiveData<Product>()
    val productInDetails: LiveData<Product> = _productInDetails

    fun getProductById(id: Int) {
        _status.value = LoadingStatus.Loading
        try {
            viewModelScope.launch {
                _productInDetails.value = service.getProductById(id)
                _status.value = LoadingStatus.Success
                Log.i("DetailsFragment", productInDetails.value.toString())
            }
        } catch (e: NullPointerException) {
            _status.value = LoadingStatus.Failed
        }
    }

    fun sendToCart(product: Product){
        val cartProduct = CartProduct(
            product.id,
            product.title,
            product.image,
            product.price
        )
        viewModelScope.launch {
            repository.addToCart(cartProduct)
        }
    }
}