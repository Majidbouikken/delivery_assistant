package com.example.deliveryassistant

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.Product

@Database(entities = arrayOf(Order::class, Product::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ordersDao(): OrdersDao
}