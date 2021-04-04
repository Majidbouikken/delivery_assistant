package com.example.deliveryassistant.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// classe produit
@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val quantity: Int,
    val unitPrice: Long
) {
}