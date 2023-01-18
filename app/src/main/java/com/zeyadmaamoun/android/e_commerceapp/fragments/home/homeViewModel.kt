package com.zeyadmaamoun.android.e_commerceapp.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeyadmaamoun.android.e_commerceapp.models.Product
import com.zeyadmaamoun.android.e_commerceapp.remote.ProductsApiService
import kotlinx.coroutines.launch

enum class Categories{
    All,Electronics,Jewelery,Men,Women
}

class HomeViewModel(private val service: ProductsApiService) : ViewModel() {

    //only products showing on the screen after being filtered.
    private val _filteredProducts = MutableLiveData<List<Product>>(emptyList())
    val filteredProducts: LiveData<List<Product>> = _filteredProducts

    //all list of products.
    private var allList = listOf<Product>()

    //holding the current category value.
    private var currentCategory = Categories.All

    init {
        if (allList.isEmpty()) {
            getProducts()
        }
    }

    //responsible for getting data from remote resource --> service instance provided in the constructor.
    private fun getProducts() {
        viewModelScope.launch {
            allList = service.getProducts()
            _filteredProducts.value = allList
            Log.i("HomeFragment", allList.toString())
            Log.i("HomeFragment", _filteredProducts.value.toString())
        }
    }

    //responsible for changing the current category user selected.
    fun changeCategory(category: Categories){
        currentCategory = category
    }
}