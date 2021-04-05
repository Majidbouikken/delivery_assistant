package com.example.deliveryassistant.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.deliveryassistant.R
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.adapters.OrderAdapter
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.viewModels.OrdersViewModel
import kotlinx.android.synthetic.main.fragment_orders.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersFragment : Fragment() {
    lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initRecyclerView()
        getOrdersData()
        super.onActivityCreated(savedInstanceState)
    }

    @SuppressLint("WrongConstant")
    private fun initRecyclerView() {
        ordersRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), OrientationHelper.HORIZONTAL, false)
        orderAdapter = OrderAdapter(requireActivity())
        ordersRecyclerView.adapter = orderAdapter
        /*val orders : List<Order> = mutableListOf(
            Order(
                1234563415,
                "Abderrahman Errached Tlili",
                "Rue st. michelle, Errahmani, Alger",
                "+213 560 83 43 26",
                "ha_tlili@esi.dz",
                "021651234528"
            ),
            Order(
                1234563415,
                "Abderrahman Errached Tlili",
                "Rue st. michelle, Errahmani, Alger",
                "+213 560 83 43 26",
                "ha_tlili@esi.dz",
                "021651234528"

            ),
            Order(
                1234563415,
                "Abderrahman Errached Tlili",
                "Rue st. michelle, Errahmani, Alger",
                "+213 560 83 43 26",
                "ha_tlili@esi.dz",
                "021651234528",
                mutableListOf(
                    Product(
                        1,
                        "iPhone 11 pro",
                        2,
                        225000
                    ),
                    Product(
                        1,
                        "Apple Airpod pro",
                        2,
                        75000
                    )
                )
            ),
            Order(
                1234563415,
                "Abderrahman Errached Tlili",
                "Rue st. michelle, Errahmani, Alger",
                "+213 560 83 43 26",
                "ha_tlili@esi.dz",
                "021651234528",
                mutableListOf(
                    Product(
                        1,
                        "iPhone 11 pro",
                        2,
                        225000
                    ),
                    Product(
                        1,
                        "Apple Airpod pro",
                        2,
                        75000
                    )
                )
            )
        )*/
        //you_got_n_orders_left.text = "You've got "+ EnglishNumberToWords.convert(orders.size.toLong())+" orders left"
    }

    private fun getOrdersData() {
        showProgressBar()
        val viewModel = ViewModelProvider(
            this
        ).get(OrdersViewModel::class.java)
        viewModel.getOrderListDataObserver().observe(viewLifecycleOwner, Observer<List<Order>> {
            if (it.isNotEmpty()) {
                hideProgressBar()
                orderAdapter.setListData(it as ArrayList<Order>)
                orderAdapter.notifyDataSetChanged()

            } else {
                Toast.makeText(context, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }

        })
        viewModel.getOrders(1)
    }
    /*private fun getOrdersData(user_id: Int) {
        val call = RetrofitService.endpoint.getOrders(user_id)
        showProgressBar()
        call.enqueue(object : Callback<List<Order>> {
            @SuppressLint("WrongConstant")
            override fun onResponse(
                call: Call<List<Order>>?, response:
                Response<List<Order>>?
            ) {
                showProgressBar()
                val list = response?.body()!!
                if (list.isNotEmpty()) {
                    orderAdapter.setListData(list as ArrayList<Order>)
                    orderAdapter.notifyDataSetChanged()
                    /*val adapter = OrderAdapter(requireActivity(), list)
                    val layoutManager = LinearLayoutManager(requireActivity(), OrientationHelper.HORIZONTAL, false)
                    val ordersRecyclerView = activity?.findViewById<RecyclerView>(R.id.ordersRecyclerView)
                    if (ordersRecyclerView != null) {
                        ordersRecyclerView.layoutManager = layoutManager
                        ordersRecyclerView.adapter = adapter
                    }*/
                }
            }

            override fun onFailure(call: Call<List<Order>>?, t: Throwable?) {
                hideProgressBar()
                //Snackbar.make(application, "Replace with your own action", Snackbar.LENGTH_LONG)
                Toast.makeText(
                    context,
                    t.toString(),
                    Toast.LENGTH_SHORT
                )

            }
        })
    }*/

    private fun showProgressBar() {
        orders_progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        orders_progressBar.visibility = View.INVISIBLE
    }

}