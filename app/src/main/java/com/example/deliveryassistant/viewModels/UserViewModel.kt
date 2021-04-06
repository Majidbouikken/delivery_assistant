package com.example.deliveryassistant.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.UsersDao
import com.example.deliveryassistant.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    var userListData: MutableLiveData<User> = MutableLiveData()


    fun getUserDataObserver(): LiveData<User> {
        return userListData
    }

    /*fun login(email: String, password: String) {
        val call = RetrofitService.endpoint.login(email, password )
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {

                    userListData.postValue(response.body())
                } else {
                    userListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                // Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
                userListData.postValue(null)
            }
        })
    }*/
}