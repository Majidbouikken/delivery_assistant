package com.example.deliveryassistant

import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.Product
import com.example.deliveryassistant.models.User
import com.example.deliveryassistant.models.UserDashboard
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    // 1 Authentication endpoint
    @GET("login/{email}/{password}")
    fun login(@Path("email") email: String, @Path("password") password: String): Call<List<User>>

    // 2 Endpoints to display orders and products
    @GET("getOrders/{user_id}")
    fun getOrders(@Path("user_id") user_id: Int): Call<List<Order>>

    @GET("getProducts/{order_id}")
    fun getProducts(@Path("order_id") order_id: Int): Call<List<Product>>

    // 3 Endpoints to validate a delivery
    @POST("order/update/{order_id}/{barcode}")
    fun updateOrder(
        @Path("order_id") order_id: Int,
        @Path("barcode") barcode: String
    ): Call<String>

    // 4 Enpoints of the Dashboard
    @GET("getDashboardData/{user_id}")
    fun getDashboardData(@Path("user_id") user_id: Int): Call<List<UserDashboard>>
}