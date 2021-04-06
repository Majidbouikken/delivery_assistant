package com.example.deliveryassistant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryassistant.R
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.adapters.ProductAdapter
import com.example.deliveryassistant.models.Product
import kotlinx.android.synthetic.main.activity_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val orderId = intent.getStringExtra("order_id")
        if (orderId != null) {
            loadProducts(orderId.toInt())
        }
    }

    private fun loadProducts(order_id: Int) {
        val call = RetrofitService.endpoint.getProducts(order_id)
        showProgressBar()
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                call: Call<List<Product>>?, response:
                Response<List<Product>>?
            ) {
                val list = response?.body()!!
                if (list.isEmpty()) {
                    hideProgressBar()
                }
                else {
                    hideProgressBar()
                    val adapter = ProductAdapter(application, list)
                    val layoutManager = LinearLayoutManager(application)
                    productsRecyclerView.layoutManager = layoutManager
                    productsRecyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Product>>?, t: Throwable?) {
            }
        })
    }

    private fun showProgressBar() {
        product_progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        product_progressBar.visibility = View.INVISIBLE
    }
}