package com.example.deliveryassistant.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users")
data class User(
        @PrimaryKey(autoGenerate = false)
        val id: Int?,
        val first_name: String?,
        val last_name: String?,
        val email: String?,
        val password: String?,
        val avatar_url: String?
)