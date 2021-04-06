package com.example.deliveryassistant.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.deliveryassistant.MainActivity
import com.example.deliveryassistant.R
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.models.User
import com.example.deliveryassistant.models.UserDashboard
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val call = RetrofitService.endpoint.login("ha_tlili@esi.dz", "hatlili123")
            call.enqueue(object : Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>?, response:
                    Response<List<User>>?
                ) {
                    val user = response?.body()!!
                    if (response.isSuccessful) {
                        intent.putExtra("user_id", user.first().id.toString())
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(application, "Wrong info", Toast.LENGTH_LONG).show()
                        //log(response?.body()!![0].phone,response?.body()!![0].password,response?.body()!![0].Nom)
                    }
                }

                override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                }
            })
        }
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

    /*
    check if user already connected
    fun checkUser():Boolean{


        val pref = this.getSharedPreferences("status"

            ,Context.MODE_PRIVATE)

        val con = pref.getBoolean("connected"

            , false)
        println(con.toString())
        return con

    }
    fun saveUser(phone :String,password:String,nom:String){
        val pref = this.getSharedPreferences("status"

            ,Context.MODE_PRIVATE)
        pref.edit {
            putBoolean("connected"
                ,true)
            putString("phone",phone)
            putString("password",password)
            putString("nom",nom)


        }
    }*/
}