package com.zeyadmaamoun.android.e_commerceapp.fragments.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zeyadmaamoun.android.e_commerceapp.data.CartProduct
import com.zeyadmaamoun.android.e_commerceapp.remote.ProductsApiService
import com.zeyadmaamoun.android.e_commerceapp.repository.CartProductsRepository


class CartViewModel(
    private val service: ProductsApiService,
    private val repo: CartProductsRepository
) : ViewModel() {

    private val _cartProds = MutableLiveData<List<CartProduct>>(emptyList())
    val cartProds: LiveData<List<CartProduct>> = _cartProds

    private val cartProducts = listOf(
        CartProduct(
            1,
            "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            109.95
        ),
        CartProduct(
            2,
            "Mens Casual Premium Slim Fit T-Shirts ",
            "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg",
            22.3
        ),
        CartProduct(
            3,
            "Mens Cotton Jacket",
            "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
            55.99
        )
    )

    init {
        _cartProds.value = cartProducts
    }
}