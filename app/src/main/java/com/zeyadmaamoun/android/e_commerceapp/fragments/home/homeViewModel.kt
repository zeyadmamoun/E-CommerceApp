package com.zeyadmaamoun.android.e_commerceapp.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeyadmaamoun.android.e_commerceapp.models.Product
import com.zeyadmaamoun.android.e_commerceapp.remote.ProductsApiService
import kotlinx.coroutines.launch

class HomeViewModel(private val service: ProductsApiService) : ViewModel() {

    //only products showing on the screen after being filtered.
    private val _filteredProducts = MutableLiveData<List<Product>>(emptyList())
    val filteredProducts: LiveData<List<Product>> = _filteredProducts

    //all list of products.
    private var allList = listOf<Product>()

    //holding the current category value.
    private var currentCategory = "all"

    //responsible for getting data from remote resource --> service instance provided in the constructor.
    fun getProducts() {
        if (allList.isEmpty()) {
            viewModelScope.launch {
                allList = service.getProducts()
                _filteredProducts.value = allList
            }
        }
    }

    //responsible for changing the current category user selected.
    fun changeCategory(category: String) {
        currentCategory = category
        filterByCategory()
    }

    //responsible for filtering the data due to current category variable.
    private fun filterByCategory() {
        if (currentCategory == "all") {
            _filteredProducts.value = allList
        } else {
            val filtered = allList.filter { it.category == currentCategory }
            _filteredProducts.value = filtered
        }
    }
}