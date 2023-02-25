package com.zeyadmaamoun.android.e_commerceapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartProduct::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {

    abstract fun cartDao(): CartProductsDao

    companion object{
        @Volatile
        private var INSTANCE: CartDatabase? = null

        fun getDatabase(context: Context): CartDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    CartDatabase::class.java,
                    "cart_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}