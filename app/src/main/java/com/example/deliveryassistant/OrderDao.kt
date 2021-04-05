package com.example.deliveryassistant

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.deliveryassistant.models.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface OrdersDao {

    @Query("SELECT * FROM orders")
    fun getAllOrders(): Flow<List<Order>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orders: List<Order>)

    @Query("DELETE FROM orders")
    suspend fun deleteAllOrders()
}