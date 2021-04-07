package com.example.deliveryassistant.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.deliveryassistant.MainActivity
import com.example.deliveryassistant.R
import com.example.deliveryassistant.constant.NOM_FICHER_LOGIN
import com.example.deliveryassistant.models.User
import com.example.deliveryassistant.utils.SharedPreferencesInterface
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(), SharedPreferencesInterface {
    lateinit var user: User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fetch data from sharedPreferences
        user = getUser(requireContext())
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // updating UI
        profile_name.text = (user.first_name.toString() + " " + user.last_name.toString()).trim()
        profile_email.text = user.email.toString().trim()
        // the image
        Glide.with(requireContext())
            .load(user.avatar_url.toString())
            .circleCrop()
            .placeholder(R.drawable.circle)
            .into(profile_avatar)

        // logout
        profile_logout.setOnClickListener {
            logOut()
        }
        super.onActivityCreated(savedInstanceState)
    }

    private fun logOut() {
        val pref = sharedPreference(requireContext(), NOM_FICHER_LOGIN)
        pref.deleteData()
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
    }
}