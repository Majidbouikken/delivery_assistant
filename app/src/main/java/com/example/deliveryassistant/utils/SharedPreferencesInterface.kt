package com.example.deliveryassistant.utils

import android.content.Context
import com.example.deliveryassistant.constant.NOM_FICHER_LOGIN
import com.example.deliveryassistant.models.User
import java.math.BigInteger

interface SharedPreferenceInterface {

    /**
     * retourne une instance de ShaeredPrefernceHelper en entrant le nom de Fichier
     */
    fun sharedPreference(context: Context, nomFichier : String) : SharedPreferencesHelper{
        return SharedPreferencesHelper(context,nomFichier)
    }

    /**
     * avoir le sinfo sous forme de Automobolisite
     */
    fun getUser(context : Context) : User {
        val pref = sharedPreference(context, NOM_FICHER_LOGIN)
        return pref.getUser()
    }


    fun getUserId(context: Context) : Int?{
        val pref = sharedPreference(context, NOM_FICHER_LOGIN)
        return pref.getUserId()
    }
}