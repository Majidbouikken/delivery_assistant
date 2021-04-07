package com.example.deliveryassistant.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.deliveryassistant.models.User

class SharedPreferencesHelper(internal var context: Context, nom_fichier: String) {
    internal var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(nom_fichier, Context.MODE_PRIVATE)

    /**
     * To set login details
     * @param userName : username to set
     * @param password : password to set
     */
    fun saveLoginDetails(
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

    fun getUser(): User {
        val pref = sharedPreferences
        val id = pref.getString("user_id", null)?.toInt()
        val first_name = pref.getString("user_first_name", null)
        val last_name = pref.getString("user_last_name", null)
        val email = pref.getString("user_email", null)
        val avatar_url = pref.getString("user_avatar_url", null)
        return User(
            id, first_name, last_name, email, "", avatar_url
        )
    }

    fun getUserId(): Int? {
        val pref = sharedPreferences
        return pref.getString("user_id", null)?.toInt()
    }

    /**
     * To check and get login details
     * @param userName : name to validate
     * @param password : password to validate
     * @return true : if valid user
     * false : if valid password
     */
    /*fun isValidUser(userName: String, password: String): Boolean {
        // to get username
        Log.d(TAG, "username = " + sharedPreferences.getString("userName", null)!!)
        Log.d(TAG, "password = " + sharedPreferences.getString("password", null)!!)

        return this.sharedPreferences.getString(
            "userName",
            null
        ) == userName && sharedPreferences.getString("password", null) == password
    }*/


    companion object {
        private val TAG = "SharedPreferencesHelper"
    }

}