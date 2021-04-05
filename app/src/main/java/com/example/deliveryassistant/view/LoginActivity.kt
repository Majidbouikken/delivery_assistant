package com.example.deliveryassistant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.deliveryassistant.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun validateEmail(): Boolean {
        val emailInput: String = input_email.text.toString().trim()

        return if (emailInput.isEmpty()) {
            input_email.error = "email field can't be empty"
            false
        } else {
            input_email.error = null
            true
        }
    }

    fun validatePassword(): Boolean {
        val passwordInput: String = input_password.text.toString().trim()

        return if (passwordInput.isEmpty()) {
            input_password.error = "email field can't be empty"
            false
        } else {
            input_password.error = null
            true
        }
    }

    fun confirmInput(view: View) {
        if (!validateEmail() || !validatePassword()) return

        val input = input_email.text.toString()+" "+input_password.text.toString()
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show()
    }
}