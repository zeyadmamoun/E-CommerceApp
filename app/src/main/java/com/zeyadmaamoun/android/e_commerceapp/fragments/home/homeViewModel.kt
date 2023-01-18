package com.zeyadmaamoun.android.e_commerceapp.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeyadmaamoun.android.e_commerceapp.models.Product
import com.zeyadmaamoun.android.e_commerceapp.remote.ProductsApiService
import kotlinx.coroutines.launch

class HomeViewModel(private val service: ProductsApiService) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>(emptyList())
    val products: LiveData<List<Product>> = _products

    init {
        if (_products.value!!.isEmpty()) {
            getProducts()
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            _products.value = service.getProducts()
            Log.i("HomeFragment", _products.value.toString())
        }
    }
}