package com.example.deliveryassistant.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliveryassistant.UsersDao
import com.example.deliveryassistant.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    var userData: MutableLiveData<User> = MutableLiveData()


    fun getUserDataObserver(): LiveData<User> {
        return userData
    }

    /*fun login(email: String, password: String) {
        val call = RetrofitService.endpoint.login(email, password)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    userData.postValue(response.body())
                } else {
                    userData.postValue(null)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
                userData.postValue(null)
            }
        })
    }*/

    // Room functions to cache data
    /*private fun insertUser(user: User) {
        RoomService.appDatabase.usersDao().insertUser(user)
    }

    private  fun getUser(user: User): List<User> {
        return RoomService.appDatabase.usersDao().getUser()
    }*/
}