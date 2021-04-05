package com.example.deliveryassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.deliveryassistant.view.DashboardFragment
import com.example.deliveryassistant.view.OrdersFragment
import com.example.deliveryassistant.view.MapFragment
import com.example.deliveryassistant.view.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav_menu.setItemSelected(R.id.bottom_nav_dashboard, true)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DashboardFragment()).commit()
        bottomNavigationMenu()
    }

    private fun bottomNavigationMenu() {
        bottom_nav_menu.setOnItemSelectedListener {
            var fragment : Fragment = Fragment()
            when (it) {
                R.id.bottom_nav_dashboard -> fragment = DashboardFragment()
                R.id.bottom_nav_orders -> fragment = OrdersFragment()
                R.id.bottom_nav_map -> fragment = MapFragment()
                R.id.bottom_nav_profile -> fragment = ProfileFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        }
    }

}