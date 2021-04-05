package com.example.deliveryassistant.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// classe Commande
@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val first_name: String,
    val last_name: String,
    val email: String,
    val address: String,
    val phone_number: String,
    val barcode: String,
    val avatar_url: String,
    val total_price: Int
) {
}