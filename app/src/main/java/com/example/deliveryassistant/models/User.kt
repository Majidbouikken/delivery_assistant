package com.example.deliveryassistant.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user")
@Parcelize
data class User(
        @PrimaryKey(autoGenerate = false) val id: Int,
        val firstName: String,
        val lastName: String,
        val userName: String,
        val email: String,
        val password: String
) : Parcelable {
}