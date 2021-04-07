package com.example.deliveryassistant.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.deliveryassistant.R
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.constant.NOM_FICHER_LOGIN
import com.example.deliveryassistant.models.User
import com.example.deliveryassistant.utils.SharedPreferenceInterface
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), SharedPreferenceInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // making sure the dialog is hidden
        hideDialog()

        // login_button listener
        login_button.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            val call = RetrofitService.endpoint.login("oussa66@gmail.com", "oussama31")
            showDialog()
            call.enqueue(object : Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>?, response:
                    Response<List<User>>?
                ) {
                    hideDialog()
                    if (response!!.isSuccessful) {
                        val user = response.body()!!.first()
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
                    hideDialog()
                }
            })
        }

        val textWatcher1 = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                //watchers[1] = s.isNotEmpty()
                //addActorButton.isEnabled = watchers[0] && watchers[1]
            }
        }
        // editFirstName.addTextChangedListener(textWatcher1)
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

    // showDialog
    private fun showDialog() {
        login_button.visibility = View.INVISIBLE
        shadow.visibility = View.VISIBLE
        dialog.visibility = View.VISIBLE
        dialog_message.visibility = View.VISIBLE
        dialog_progressBar.visibility = View.VISIBLE
    }

    // hideDialog
    private fun hideDialog() {
        login_button.visibility = View.VISIBLE
        shadow.visibility = View.INVISIBLE
        dialog.visibility = View.INVISIBLE
        dialog_message.visibility = View.INVISIBLE
        dialog_progressBar.visibility = View.INVISIBLE
    }

    // Save user data
    private fun saveUserData(user: User) {
        val pref = sharedPreference(this, NOM_FICHER_LOGIN)
        pref.saveLoginDetails(
            user.id.toString(), user.first_name!!, user.last_name!!, user.email!!, user.avatar_url!!
        )
    }
}