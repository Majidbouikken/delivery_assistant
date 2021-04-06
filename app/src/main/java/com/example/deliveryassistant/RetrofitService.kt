package com.example.deliveryassistant

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val endpoint: Endpoint by lazy {
        Retrofit.Builder().baseUrl("https://f4801262c885.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(Endpoint::class.java)
    }
}