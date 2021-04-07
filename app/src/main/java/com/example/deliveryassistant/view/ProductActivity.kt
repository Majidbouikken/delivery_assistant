package com.example.deliveryassistant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deliveryassistant.R
import com.example.deliveryassistant.retrofit.RetrofitService
import com.example.deliveryassistant.adapters.ProductAdapter
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.Product
import com.example.deliveryassistant.viewModels.ProductsViewModel
import kotlinx.android.synthetic.main.activity_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val orderId = intent.getStringExtra("order_id")
        ic_return.setOnClickListener {
            this?.onBackPressed()
        }
        initRecyclerView()
        if (orderId != null) {
            getProductsData(orderId.toInt())

        }
    }

    private fun initRecyclerView() {
        productsRecyclerView.layoutManager =
            LinearLayoutManager(application)
        productAdapter = ProductAdapter(application)
        productsRecyclerView.adapter = productAdapter
    }

    private fun getProductsData(orderId: Int?) {
        showProgressBar()
        val viewModel = ViewModelProvider(
            this
        ).get(ProductsViewModel::class.java)
        viewModel.getProductsListDataObserver()
            .observe(this, Observer<List<Product>> { result ->
                if (!result.isNullOrEmpty()) {
                    hideProgressBar()

                    productAdapter.setListData(result as ArrayList<Product>)
                    productAdapter.notifyDataSetChanged()

                } else {
                    hideProgressBar()
                }

            })
        viewModel.getProducts(orderId!!)
    }

    // method to show progress bar
    private fun showProgressBar() {
        product_progressBar.visibility = View.VISIBLE
    }

    // method to hide progress bar
    private fun hideProgressBar() {
        product_progressBar.visibility = View.INVISIBLE
    }
}