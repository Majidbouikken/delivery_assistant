package com.example.deliveryassistant.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.deliveryassistant.models.User

class SharedPreferencesHelper(internal var context: Context, nom_fichier: String) {
    internal var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(nom_fichier, Context.MODE_PRIVATE)


    // to save login details after authentication
    fun saveUserData(
        id: String,
        first_name: String,
        last_name: String,
        email: String,
        avatar_url: String
    ) {
        val editor = sharedPreferences.edit()
        editor.putString("user_id", id)
        editor.putString("user_first_name", first_name)
        editor.putString("user_last_name", last_name)
        editor.putString("user_email", email)
        editor.putString("user_avatar_url", avatar_url)
        editor.apply()
    }

    // to get the user data (useful for displaying ui)
    fun getUser(): User {
        val pref = sharedPreferences
        val id = pref.getString("user_id", null)?.toInt()
        val firstName = pref.getString("user_first_name", null)
        val lastName = pref.getString("user_last_name", null)
        val email = pref.getString("user_email", null)
        val avatarUrl = pref.getString("user_avatar_url", null)
        return User(
            id, firstName, lastName, email, "", avatarUrl
        )
    }

    // to get the user id for further use (like GET and POST requests)
    fun getUserId(): Int? {
        val pref = sharedPreferences
        return pref.getString("user_id", null)?.toInt()
    }

    // function used to delete the whole thing
    fun deleteData() {
        val pref = sharedPreferences
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }


    companion object {
        private val TAG = "SharedPreferencesHelper"
    }

}