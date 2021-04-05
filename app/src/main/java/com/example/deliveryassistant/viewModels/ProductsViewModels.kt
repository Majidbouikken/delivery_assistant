package com.example.deliveryassistant.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModels : ViewModel() {

    var productsListData : MutableLiveData<List<Product>> = MutableLiveData()

    fun getProductsListDataObserver(): LiveData<List<Product>> {
        return productsListData
    }

    fun getProducts(order_id: Int) {
        val call = RetrofitService.endpoint.getProducts(order_id)
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    productsListData.postValue(response.body())
                } else {
                    productsListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                // Toast.makeText(this@RecyclerViewActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
                productsListData.postValue(null)
            }
        })
    }
}