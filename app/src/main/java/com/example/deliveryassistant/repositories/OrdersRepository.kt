package com.example.deliveryassistant.repositories

import com.example.deliveryassistant.AppDatabase
import com.example.deliveryassistant.Endpoint
import com.example.deliveryassistant.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val api: Endpoint,
    private val db: AppDatabase
) {
    private val ordersDao = db.ordersDao()

    fun getOrders() = networkBoundResource(
        query = {
            ordersDao.getAllOrders()
        },
        fetch = {
            delay(2000)
            api.getOrders(1)
        },
        saveFetchResult = { orders ->
            ordersDao.deleteAndInsert(orders)
        }
    )
}