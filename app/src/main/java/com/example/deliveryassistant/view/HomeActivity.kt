package com.example.deliveryassistant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.deliveryassistant.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // setting up the bottom navigation bar
        bottom_nav_menu.setItemSelected(R.id.bottom_nav_dashboard, true)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DashboardFragment()).commit()
        bottomNavigationMenu()
    }

    private fun bottomNavigationMenu() {
        bottom_nav_menu.setOnItemSelectedListener {
            var fragment: Fragment = Fragment()
            when (it) {
                R.id.bottom_nav_dashboard -> fragment = DashboardFragment()
                R.id.bottom_nav_orders -> fragment = OrdersFragment()
                R.id.bottom_nav_map -> fragment = MapFragment()
                R.id.bottom_nav_profile -> fragment = ProfileFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

}