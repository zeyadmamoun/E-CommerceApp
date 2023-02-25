package com.zeyadmaamoun.android.e_commerceapp.fragments.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zeyadmaamoun.android.e_commerceapp.databinding.CartItemBinding
import com.zeyadmaamoun.android.e_commerceapp.data.CartProduct

class CartProductsAdapter(
    featureListener: CartItemFeatures,
    private val onItemClicked: (CartProduct)-> Unit = {}
) : ListAdapter<CartProduct, CartProductsAdapter.CartProductViewHolder>(DiffCallback) {

    private val cartItemFeaturesListener: CartItemFeatures
    init {
        cartItemFeaturesListener = featureListener
    }

    class CartProductViewHolder(
        private val binding: CartItemBinding,
        private val featureListener: CartItemFeatures
    ) :RecyclerView.ViewHolder(binding.root){
        fun bind(cartProduct: CartProduct){
            binding.cartProduct = cartProduct
            binding.executePendingBindings()
        }
        fun productAddition(cartProduct: CartProduct){        // we user click on add button
            binding.addUnit.setOnClickListener {
                featureListener.addUnit(cartProduct)
            }
        }
        fun productDeletion(cartProduct: CartProduct){        // when user click on delete button
            binding.removeUnit.setOnClickListener {
                featureListener.deleteUnit(cartProduct)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        return CartProductViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),cartItemFeaturesListener)
    }

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
        holder.productAddition(product)
        holder.productDeletion(product)
        holder.itemView.setOnClickListener {
            onItemClicked(product)
        }
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