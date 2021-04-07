package com.example.deliveryassistant.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.deliveryassistant.R
import com.example.deliveryassistant.RetrofitService
import com.example.deliveryassistant.ScanActivity
import com.example.deliveryassistant.models.Order
import com.example.deliveryassistant.models.User
import com.example.deliveryassistant.models.UserDashboard
import com.example.deliveryassistant.utils.ConnectivityStatus
import com.example.deliveryassistant.utils.DateParser
import com.example.deliveryassistant.utils.EnglishNumberToWords
import com.example.deliveryassistant.utils.SharedPreferencesInterface
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardFragment : Fragment(), SharedPreferencesInterface {
    lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fetch data from sharedPreferences
        user = getUser(requireContext())
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // making sure the dialog is hidden
        hideDialog()
        // show a warning if there's no internet connection
        if (!ConnectivityStatus.isOnline()) showInternetWarning()
        // Update UI to user data
        welcome_first_name.text = (user.first_name.toString() + "!").trim()
        // get dashboard data
        loadUserDashboard(user.id!!)
        // barcode scanner
        scanActionButton.setOnClickListener {
            val integrator: IntentIntegrator = IntentIntegrator.forSupportFragment(this)
            integrator.captureActivity = ScanActivity::class.java
            integrator.setOrientationLocked(false)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            integrator.setPrompt("Scan the code bar on the package")
            integrator.initiateScan()
        }
        super.onActivityCreated(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        val content = result.contents
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(activity, "Scanning failed", Toast.LENGTH_SHORT).show()
            } else {
                validateOrder(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    // function to validate an order
    private fun validateOrder(barcode: String) {
        val call = RetrofitService.endpoint.updateOrder(
            Order(
                0,
                "",
                "",
                "",
                "",
                "",
                barcode,
                "",
                0,
                "",
                "",
                0.0,
                0.0
            )
        )
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>?, response:
                Response<String>?
            ) {
                if (response!!.isSuccessful) {
                    showDialog()
                    val handler = Handler(Looper.getMainLooper()).postDelayed({
                        hideDialog()
                    }, 3000)
                } else {
                    Toast.makeText(activity, "Codebar didn't match the order", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Toast.makeText(activity, "Scanning failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // function to load dashboard data such as delayed deliveries and remaining deliveries

    private fun loadUserDashboard(user_id: Int) {
        // getting the delay date
        val delaydate = DateParser.dateToString(-1)
        // setting up the call
        val call = RetrofitService.endpoint.getDashboardData(delaydate, user_id)
        call.enqueue(object : Callback<List<UserDashboard>> {
            override fun onResponse(
                call: Call<List<UserDashboard>>?, response:
                Response<List<UserDashboard>>?
            ) {
                if (response!!.isSuccessful) {
                    // setting up dashboard data
                    val dashboardData = response.body()!!
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

    // show Order validation dialog
    private fun showDialog() {
        scanActionButton.visibility = View.INVISIBLE
        shadow_dashboard.visibility = View.VISIBLE
        dialog_dashboard.visibility = View.VISIBLE
        dialog_dashboard_icon.visibility = View.VISIBLE
        dialog_dashboard_message.visibility = View.VISIBLE
    }

    // hide Order validation dialog
    private fun hideDialog() {
        scanActionButton.visibility = View.VISIBLE
        shadow_dashboard.visibility = View.INVISIBLE
        dialog_dashboard.visibility = View.INVISIBLE
        dialog_dashboard_icon.visibility = View.INVISIBLE
        dialog_dashboard_message.visibility = View.INVISIBLE
    }
}