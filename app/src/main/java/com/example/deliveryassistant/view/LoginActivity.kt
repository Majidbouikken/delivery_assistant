package com.example.deliveryassistant.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.deliveryassistant.R
import com.example.deliveryassistant.retrofit.RetrofitService
import com.example.deliveryassistant.constant.NOM_FICHER_LOGIN
import com.example.deliveryassistant.models.User
import com.example.deliveryassistant.utils.SharedPreferencesInterface
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), SharedPreferencesInterface {
    var watchers = mutableListOf<Boolean>(false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // making sure the dialog is hidden
        hideDialog()

        // client side verification
        val emailTextWatcher = object : TextWatcher {
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
                watchers[0] = validateEmail(s.toString())
                login_button.isEnabled = watchers[0] && watchers[1]
            }
        }
        val passwordTextWatcher = object : TextWatcher {
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
                watchers[1] = validatePassword(s.toString())
                login_button.isEnabled = watchers[0] && watchers[1]
            }
        }
        input_email.addTextChangedListener(emailTextWatcher)
        input_password.addTextChangedListener(passwordTextWatcher)

        // login_button listener
        login_button.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            val call = RetrofitService.endpoint.login("ha_tlili@esi.dz", "hatlili123")
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
                        Toast.makeText(application, "Wrong email or password", Toast.LENGTH_LONG).show()
                        //log(response?.body()!![0].phone,response?.body()!![0].password,response?.body()!![0].Nom)
                    }
                }

                override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                    hideDialog()
                }
            })
        }
    }

    // client side validators
    fun validateEmail(s: String): Boolean = s.isNotEmpty()
    fun validatePassword(s: String): Boolean = s.isNotEmpty()

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
        pref.saveUserData(
            user.id.toString(), user.first_name!!, user.last_name!!, user.email!!, user.avatar_url!!
        )
    }
}