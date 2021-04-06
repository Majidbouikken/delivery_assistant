package com.example.deliveryassistant.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.deliveryassistant.R
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.ScanActivity
import com.example.deliveryassistant.models.UserDashboard
import com.example.deliveryassistant.utils.ConnectivityStatus
import com.example.deliveryassistant.utils.DateParser
import com.example.deliveryassistant.utils.EnglishNumberToWords
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*


class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // show a warning if there's no internet connection
        if (!ConnectivityStatus.isOnline()) showInternetWarning()
        // barcode scanner
        scanActionButton.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator(activity)
            integrator.captureActivity = ScanActivity::class.java
            integrator.setOrientationLocked(false)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            integrator.setPrompt("Scanning Code")
            integrator.initiateScan()
        }
        // get dashboard data
        loadOrdersCount(1)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                val builder = AlertDialog.Builder(activity)
                builder.setMessage(result.contents)
                builder.setTitle("Scanning result")
                val dialog = builder.create()
                dialog.show()
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadOrdersCount(user_id: Int) {
        // getting the delay date
        val delaydate = DateParser.dateToString(-1)
        // setting up the call
        val call = RetrofitService.endpoint.getDashboardData(delaydate, user_id)
        call.enqueue(object : Callback<List<UserDashboard>> {
            override fun onResponse(
                call: Call<List<UserDashboard>>?, response:
                Response<List<UserDashboard>>?
            ) {
                val dashboardData = response?.body()!!
                if (response.isSuccessful) {
                    // setting up dashboard data
                    val total = dashboardData.first().total
                    val delivered = dashboardData.first().delivered
                    val remaining = total - delivered
                    val delay = dashboardData.first().delay
                    // updating the view
                    dashboard_orderCount.text = remaining.toString()
                    dashboard_deliveryCount.text = delivered.toString()
                    if (delay > 0) {
                        showDelayWarning()
                        delayed_count.text = EnglishNumberToWords.convert(delay.toLong())
                        delayed_text.text = when (delay) {
                            1 -> getString(R.string.delivery_is_delayed_by_48_hours)
                            else -> getString(R.string.deliveries_are_delayed_by_48_hours)
                        }
                    } else {
                        hideDelayWarning()
                    }
                }
            }

            override fun onFailure(call: Call<List<UserDashboard>>?, t: Throwable?) {
            }
        })
    }

    // show the Delayed Orders Warning
    private fun showInternetWarning() {
        no_internet_frame.visibility = View.VISIBLE
        no_internet_icon.visibility = View.VISIBLE
        no_internet_text.visibility = View.VISIBLE
    }

    // hide the Delayed Orders Warning
    private fun hideInternetWarning() {
        no_internet_frame.visibility = View.INVISIBLE
        no_internet_icon.visibility = View.INVISIBLE
        no_internet_text.visibility = View.INVISIBLE
    }

    // show the Delayed Orders Warning
    private fun showDelayWarning() {
        delayed_frame.visibility = View.VISIBLE
        delayed_icon.visibility = View.VISIBLE
        delayed_count.visibility = View.VISIBLE
        delayed_text.visibility = View.VISIBLE
    }

    // hide the Delayed Orders Warning
    private fun hideDelayWarning() {
        delayed_frame.visibility = View.INVISIBLE
        delayed_icon.visibility = View.INVISIBLE
        delayed_count.visibility = View.INVISIBLE
        delayed_text.visibility = View.INVISIBLE
    }
}