package com.zeyadmaamoun.android.e_commerceapp

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.zeyadmaamoun.android.e_commerceapp.fragments.cart.CartProductsAdapter
import com.zeyadmaamoun.android.e_commerceapp.fragments.home.ProductsListAdapter
import com.zeyadmaamoun.android.e_commerceapp.data.CartProduct
import com.zeyadmaamoun.android.e_commerceapp.models.Product

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView,imgUrl: String?){
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView : RecyclerView, data : List<Product>?){
    val adapter = recyclerView.adapter as ProductsListAdapter
    adapter.submitList(data)
}

@BindingAdapter("listCartData")
fun bindCartRecyclerView(recyclerView : RecyclerView, data : List<CartProduct>?){
    val adapter = recyclerView.adapter as CartProductsAdapter
    adapter.submitList(data)
}