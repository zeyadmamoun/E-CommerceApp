package com.zeyadmaamoun.android.e_commerceapp.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zeyadmaamoun.android.e_commerceapp.databinding.ProductsItemBinding
import com.zeyadmaamoun.android.e_commerceapp.models.Product

class ProductsListAdapter(private val onItemClicked: (Product)-> Unit) :
    ListAdapter<Product, ProductsListAdapter.ProductViewHolder>(DiffCallback) {

    class ProductViewHolder(private val binding: ProductsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product){
            binding.product = product
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onItemClicked(product)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
    }
}