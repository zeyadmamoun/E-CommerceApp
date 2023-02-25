package com.zeyadmaamoun.android.e_commerceapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_products")
data class CartProduct(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "product_name") val name: String,
    @ColumnInfo(name = "product_image") val image: String,
    @ColumnInfo(name = "product_price") val price: Double,
    @ColumnInfo(name = "units") var units: Int = 1
){
    fun shortenName(): String{
        return if (name.length > 40){
            name.dropLast(name.length - 40)
        }else{
            name
        }
    }
}
