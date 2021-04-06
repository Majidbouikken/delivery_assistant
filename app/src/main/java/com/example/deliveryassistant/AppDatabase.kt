package com.example.deliveryassistant

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deliveryassistant.models.Order

@Database(entities = [Order::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    //abstract fun usersDao(): UsersDao
    abstract fun ordersDao(): OrdersDao
}