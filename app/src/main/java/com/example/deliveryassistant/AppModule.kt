package com.example.deliveryassistant

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl("https://2458ede88822.ngrok.io/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideEndpoint(retrofit: Retrofit): Endpoint = retrofit.create(Endpoint::class.java)

    @Provides
    @Singleton
    fun providesDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "delivery_assistant")
            .allowMainThreadQueries().build()
}