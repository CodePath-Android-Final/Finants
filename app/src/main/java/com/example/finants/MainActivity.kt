package com.example.finants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.finants.fragments.FeedFragment
import com.example.finants.fragments.HomeFragment
import com.example.finants.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager


        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
                item->

            var fragmentToShow: Fragment? = null
            when (item.itemId) {
                R.id.action_feed->{
                    fragmentToShow = FeedFragment()
                }
                R.id.action_home->{
                    fragmentToShow = HomeFragment()
                }
                R.id.action_profile->{
                    fragmentToShow = ProfileFragment()
                }
            }
            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow)
                    .commit()
            }
                true
            }
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_feed
        }

}