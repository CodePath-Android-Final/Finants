package com.example.finants.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.finants.*
import com.parse.Parse.getApplicationContext
import com.parse.ParseObject
import com.parse.ParseUser
import com.parse.ParseUser.getCurrentUser

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val screenName = view.findViewById<TextView>(R.id.profileName)
        screenName.text = getCurrentUser()?.username

        val ivProfileImage = view.findViewById<ImageView>(R.id.profileImage)

        //Log.i(TAG, ParseUser.getCurrentUser()?.getParseFile("profile_pic")?.url.toString())
        //Glide.with(getApplicationContext()).load(getCurrentUser()?.getParseFile("profile_pic")?.url).into(ivProfileImage)

        //Set onclickListeners for the buttons
        val addingGoal = view.findViewById<Button>(R.id.addGoalBtn)
        addingGoal.setOnClickListener {
            val intent = Intent(requireContext(), ComposeGoal::class.java)
            startActivity(intent)
        }

        val addingIncome = view.findViewById<Button>(R.id.addIncBtn)
        addingIncome.setOnClickListener {
            val intent = Intent(requireContext(), ComposeIncome::class.java)
            startActivity(intent)
        }

        val addingExpense = view.findViewById<Button>(R.id.addExpBtn)
        addingExpense.setOnClickListener {
            val intent = Intent(requireContext(), ComposeExpense::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val TAG = "Testing"
    }
}