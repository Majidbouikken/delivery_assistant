package com.example.deliveryassistant

import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.Product
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    @GET("getorders/{user_id}")
    fun getOrders(@Path("user_id") user_id: Int): Call<List<Order>>

    @GET("getorders/{order_id}")
    fun getProducts(@Path("order_id") order_id: Int): Call<List<Product>>

    /*@GET("getmovies")
    fun getMovies(): Call<List<Movie>>

    @GET("getactors")
    fun getActors(): Call<List<Actor>>

    @POST("postactor")
    fun postActor(
        @Body actor: Actor
    ): Call<String>*/
}