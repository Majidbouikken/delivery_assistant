package com.example.deliveryassistant.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.deliveryassistant.R
import com.example.deliveryassistant.adapters.OrderAdapter
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.utils.EnglishNumberToWords
import com.example.deliveryassistant.viewModels.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_orders.*

@Suppress("DEPRECATION")
class OrdersFragment : Fragment() {
    lateinit var orderAdapter: OrderAdapter
    private val viewModel: OrdersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        viewModel.orders.observe(viewLifecycleOwner) {result ->
            orderAdapter.setListData(result.data as ArrayList<Order>)
            orderAdapter.notifyDataSetChanged()


        }
        //getOrdersData()
    }

    @SuppressLint("WrongConstant")
    private fun initRecyclerView() {
        ordersRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), OrientationHelper.HORIZONTAL, false)
        orderAdapter = OrderAdapter(requireActivity())
        ordersRecyclerView.adapter = orderAdapter
    }

    /*private fun getOrdersData() {
        showProgressBar()
        val viewModel = ViewModelProvider(
            this
        ).get(OrdersViewModel::class.java)
        viewModel.getOrderListDataObserver().observe(viewLifecycleOwner, Observer<List<Order>> {
            if (it.isNotEmpty()) {
                hideProgressBar()
                hideNoOrdersMessage()
                // update fragment subtitle
                you_got.text = getString(R.string.you_ve)
                orders_count_text.text = EnglishNumberToWords.convert(it.size.toLong())
                orders_left.text = when (it.size) {
                    1 -> getString(R.string.order_left)
                    else -> getString(R.string.orders_left)
                }
                orderAdapter.setListData(it as ArrayList<Order>)
                orderAdapter.notifyDataSetChanged()

            } else {
                hideProgressBar()
                showNoOrdersMessage()
            }

        })
        viewModel.getOrders(1)
    }*/

    private fun showProgressBar() {
        orders_progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        orders_progressBar.visibility = View.INVISIBLE
    }

    // No orders message is a message to show up when there are no deliveries today
    private fun showNoOrdersMessage() {
        no_orders_frame.visibility = View.VISIBLE
        no_orders_icon.visibility = View.VISIBLE
        no_orders_text.visibility = View.VISIBLE
    }

    private fun hideNoOrdersMessage() {
        no_orders_frame.visibility = View.INVISIBLE
        no_orders_icon.visibility = View.INVISIBLE
        no_orders_text.visibility = View.INVISIBLE
    }

}