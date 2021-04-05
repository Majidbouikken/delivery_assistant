package com.example.deliveryassistant.models

import androidx.room.Entity
import androidx.room.PrimaryKey

// classe produit
@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val unit_price: Long,
    val img_url: String,
    val quantity: Int
) {
}