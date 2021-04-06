package com.example.deliveryassistant

import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.Product
import com.example.deliveryassistant.models.User
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    // 1 Authentication endpoint
    @GET("login/{email}/{password}")
    fun login(@Path("email") email: String, @Path("password") password: String): Call<User>

    // 2 Endpoints to display orders and products
    @GET("getOrders/{user_id}")
    suspend fun getOrders(@Path("user_id") user_id: Int): List<Order>

    @GET("getProducts/{order_id}")
    suspend fun getProducts(@Path("order_id") order_id: Int): Call<List<Product>>

    // 3 Endpoints to validate a delivery
    @POST("order/update/{order_id}/{barcode}")
    fun updateOrder(
        @Path("order_id") order_id: Int,
        @Path("barcode") barcode: String
    ): Call<String>

    // 4 Enpoints of the Dashboard
    @GET("getTodayOrdersCount/{user_id}")
    fun getTodayOrdersCount(@Path("user_id") user_id: Int): Call<Int>

    @GET("getTodaysDeliveriesCount/{user_id}")
    fun getTodaysDeliveriesCount(@Path("user_id") user_id: Int): Call<Int>

    @GET("getDelayedOrdersCount/{user_id}")
    fun getDelayedOrdersCount(@Path("user_id") user_id: Int): Call<Int>
}