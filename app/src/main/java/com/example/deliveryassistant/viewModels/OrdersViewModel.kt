package com.example.deliveryassistant.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.models.Order
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersViewModel : ViewModel() {

    var orderListData: MutableLiveData<List<Order>> = MutableLiveData()


    fun getOrderListDataObserver(): LiveData<List<Order>> {
        return orderListData
    }

    fun getOrders(user_id: Int) {
        val call = RetrofitService.endpoint.getOrders(user_id)
        call.enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                if (response.isSuccessful) {
                    orderListData.postValue(response.body())
                } else {
                    orderListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                // Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
                orderListData.postValue(null)
            }
        })
    }
}