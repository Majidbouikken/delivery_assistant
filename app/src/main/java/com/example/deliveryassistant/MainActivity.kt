package com.example.deliveryassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.deliveryassistant.utils.SharedPreferencesInterface

class MainActivity : AppCompatActivity(), SharedPreferencesInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fetching userid
        val userId = getUserId(this)

        /**
         *  le code qui permet de passer a login activity
         */

        // checking whether the user was already connected
        val intent = when(userId){
            null -> Intent(this, com.example.deliveryassistant.view.LoginActivity::class.java)
            else -> Intent(this, com.example.deliveryassistant.view.HomeActivity::class.java)
        }

        // handler to keep the splash screen for a while
        val handler = Handler(Looper.getMainLooper()).postDelayed({
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }, 3000)
    }
}