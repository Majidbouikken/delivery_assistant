package com.example.deliveryassistant.utils

import android.content.Context
import com.example.deliveryassistant.constant.NOM_FICHER_LOGIN
import com.example.deliveryassistant.models.User
import java.math.BigInteger

interface SharedPreferencesInterface {

    // instanciating the shared preference thing
    fun sharedPreference(context: Context, nomFichier : String) : SharedPreferencesHelper{
        return SharedPreferencesHelper(context,nomFichier)
    }

    fun getUser(context : Context) : User {
        val pref = sharedPreference(context, NOM_FICHER_LOGIN)
        return pref.getUser()
    }

    fun getUserId(context: Context) : Int?{
        val pref = sharedPreference(context, NOM_FICHER_LOGIN)
        return pref.getUserId()
    }
}