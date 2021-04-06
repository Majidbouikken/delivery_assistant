package com.example.deliveryassistant.view

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.deliveryassistant.MainActivity
import com.example.deliveryassistant.R
import com.example.deliveryassistant.models.User
import com.example.deliveryassistant.viewModels.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*login_button.setOnClickListener {
            login(input_email.text.toString(), input_password.text.toString())
        }*/

        /*val intent = Intent(application, MainActivity::class.java)
        startActivity(intent)
        finish()*/
    }

    /*fun validateEmail(): Boolean {
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
    }*/

    /*fun login(email: String, password: String) {
        getUserData(email, password)
        startActivity(Intent(application, MainActivity::class.java))
    }

    fun getUserData(email: String, password: String) {
        val viewModel = ViewModelProvider(
            this
        ).get(UserViewModel::class.java)
        viewModel.getUserDataObserver().observe(this, Observer<User> {
            if (it != null) {
            } else {

            }

        })
        viewModel.login(email, password)

        /*if (!validateEmail() || !validatePassword()) return

        val input = input_email.text.toString()+" "+input_password.text.toString()
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show()*/
    }*/

    /*private fun getUser(): List<User> {
        return RoomService.appDatabase.usersDao().getUser()
    }*/
}