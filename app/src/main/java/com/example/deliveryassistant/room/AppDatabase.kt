package com.example.deliveryassistant.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.Product
import com.example.deliveryassistant.models.User

@Database(entities = [User::class, Order::class, Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun ordersDao(): OrdersDao
}