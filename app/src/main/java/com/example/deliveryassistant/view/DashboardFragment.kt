package com.example.deliveryassistant.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deliveryassistant.R
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.models.UserDashboard
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        loadOrdersCount(1)
        super.onActivityCreated(savedInstanceState)
    }

    private fun loadOrdersCount(user_id: Int) {
        val call = RetrofitService.endpoint.getDashboardData(user_id)
        call.enqueue(object : Callback<List<UserDashboard>> {
            override fun onResponse(
                call: Call<List<UserDashboard>>?, response:
                Response<List<UserDashboard>>?
            ) {
                val dashboardData = response?.body()!!
                if (response.isSuccessful) {
                    val total = dashboardData.first().total
                    val delivered = dashboardData.first().delivered
                    val remaining = total - delivered
                    dashboard_orderCount.text = remaining.toString()
                    dashboard_deliveryCount.text = delivered.toString()
                }
            }

            override fun onFailure(call: Call<List<UserDashboard>>?, t: Throwable?) {
                dashboard_orderCount.text = "0"
            }
        })
    }
}