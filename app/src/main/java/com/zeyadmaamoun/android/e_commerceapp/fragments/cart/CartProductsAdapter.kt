package com.zeyadmaamoun.android.e_commerceapp.fragments.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zeyadmaamoun.android.e_commerceapp.databinding.CartItemBinding
import com.zeyadmaamoun.android.e_commerceapp.models.CartProduct
import com.zeyadmaamoun.android.e_commerceapp.models.Product

class CartProductsAdapter(private val onItemClicked: (Product)-> Unit = {}) :
    ListAdapter<CartProduct, CartProductsAdapter.CartProductViewHolder>(DiffCallback) {

    class CartProductViewHolder(private val binding: CartItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(cartProduct: CartProduct){
            binding.cartProduct = cartProduct
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        return CartProductViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    object DiffCallback : DiffUtil.ItemCallback<CartProduct>() {
        override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem.id == newItem.id
        }
    }

}