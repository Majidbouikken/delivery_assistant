package com.example.deliveryassistant.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deliveryassistant.R
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.constant.NOM_FICHER_LOGIN
import com.example.deliveryassistant.models.User
import com.example.deliveryassistant.utils.SharedPreferenceInterface
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), SharedPreferenceInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        google_buttnon.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        login_button.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            val call = RetrofitService.endpoint.login("oussa66@gmail.com", "oussama31")
            call.enqueue(object : Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>?, response:
                    Response<List<User>>?
                ) {
                    val responseBody = response?.body()!!
                    if (response.isSuccessful) {
                        val user = responseBody.first()
                        saveUserData(user)
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

    // Save user data
    private fun saveUserData(user: User) {
        val pref = sharedPreference(this, NOM_FICHER_LOGIN)
        pref.saveLoginDetails(
            user.id.toString(), user.first_name!!, user.last_name!!, user.email!!, user.avatar_url!!
        )
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