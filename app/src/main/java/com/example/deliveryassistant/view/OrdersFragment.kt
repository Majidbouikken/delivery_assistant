package com.example.deliveryassistant.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryassistant.R
import com.example.deliveryassistant.adapters.OrderAdapter
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.Product
import com.example.deliveryassistant.utils.EnglishNumberToWords
import kotlinx.android.synthetic.main.fragment_orders.*

@Suppress("UNREACHABLE_CODE")
class OrdersFragment : Fragment() {
    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val orders : List<Order> = mutableListOf(
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
        )

        you_got_n_orders_left.text = "You've got "+ EnglishNumberToWords.convert(orders.size.toLong())+" orders left"

        // l'adaptateur et le layout manager
        val adapter = OrderAdapter(requireActivity(), orders)
        val layoutManager = LinearLayoutManager(requireActivity(), OrientationHelper.HORIZONTAL, false)

        // l'inflation du recyclerView et on affecte l'adaptateur et le manageur
        val recyclerView = activity?.findViewById<RecyclerView>(R.id.ordersRecyclerView)
        if (recyclerView != null) {
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        }
    }
}