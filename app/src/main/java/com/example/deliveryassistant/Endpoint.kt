package com.example.deliveryassistant

import com.example.deliveryassistant.models.Order
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    @GET("getorders/{user_id}")
    fun getOrders(@Path("user_id") user_id: Int): Call<List<Order>>

    /*@GET("getmovies")
    fun getMovies(): Call<List<Movie>>

    @GET("getactors")
    fun getActors(): Call<List<Actor>>

    @POST("postactor")
    fun postActor(
        @Body actor: Actor
    ): Call<String>*/
}