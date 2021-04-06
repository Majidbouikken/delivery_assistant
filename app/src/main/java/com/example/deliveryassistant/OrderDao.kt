package com.example.deliveryassistant

import androidx.room.*
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.Product
import kotlinx.coroutines.flow.Flow
import java.nio.file.Files.delete


@Dao
interface OrdersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orders: List<Order>)

    @Query("SELECT * FROM orders")
    fun getAllOrders(): Flow<List<Order>>

    @Query("DELETE FROM orders")
    suspend fun deleteAllOrders()

    @Transaction
    suspend fun deleteAndInsert(orders: List<Order>) {
        deleteAllOrders()
        insertOrders(orders)
    }
}