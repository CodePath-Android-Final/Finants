package com.example.finants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.finants.fragments.ProfileFragment
import com.parse.ParseUser

class ComposeIncome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_income)

        findViewById<Button>(R.id.submitIncomeBtn).setOnClickListener {
            val incomeDescription = findViewById<EditText>(R.id.incomeDesc).text.toString()
            val incomeAmount = findViewById<EditText>(R.id.incomeAmt).text.toString().toInt()
            val user = ParseUser.getCurrentUser()

            submitIncome(incomeDescription, incomeAmount, user)

            // goToProfileFragment()
        }
    }

    /* private fun goToProfileFragment() {
        val intent= Intent(this@ComposeIncome, ProfileFragment::class.java)
        startActivity(intent)
        finish()
    } */

    fun submitIncome(income_description: String, income_amount: Number, user: ParseUser) {
        //create the income object
        val income = Income()
        income.setDesc(income_description)
        income.setAmount(income_amount)
        income.setUser(user)

        //post.setImage(ParseFile(file))

        income.saveInBackground { exception ->
            if (exception != null) {
                // Something has went wrong
                Log.e(TAG, "Error while saving income")
                exception.printStackTrace()
                // TODO: Show a toast to tell user something went wrong with saving post
                Toast.makeText(this, "Something went wrong with saving the post!", Toast.LENGTH_SHORT).show()
            } else {
                Log.i(TAG, "Successfully saved income")
                //TODO: Resetting the EditText field to be empty
                //view.findViewById<EditText>(R.id.description).text.clear()
                //TODO: Reset the ImageView to empty
                //view.findViewById<ImageView>(R.id.imageView).setImageDrawable(null)

            }
        }
    }

    companion object {
        const val TAG = "ComposeIncome"
    }
}