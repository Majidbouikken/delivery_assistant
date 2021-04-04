package com.example.deliveryassistant.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// classe Commande
@Entity(tableName = "order")
data class Order(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val address: String,
    val phoneNumber: String,
    val email: String,
    val barcode: String,
    val products: List<Product>
) {
}