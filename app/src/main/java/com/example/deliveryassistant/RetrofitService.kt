package com.example.deliveryassistant

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val endpoint: Endpoint by lazy {
        Retrofit.Builder().baseUrl("https://3b2723209710.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(Endpoint::class.java)
    }
}