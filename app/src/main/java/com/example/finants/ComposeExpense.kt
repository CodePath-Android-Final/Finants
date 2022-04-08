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

class ComposeExpense : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_expense)

        findViewById<Button>(R.id.submitExpenseBtn).setOnClickListener {
            val expenseDescription = findViewById<EditText>(R.id.expenseDesc).text.toString()
            val expenseAmount = findViewById<EditText>(R.id.expenseAmt).text.toString().toInt()
            val user = ParseUser.getCurrentUser()

            submitExpense(expenseDescription, expenseAmount, user)

            // goToProfileFragment()
        }
    }

    /* private fun goToProfileFragment() {
        val intent= Intent(this@ComposeExpense,ProfileFragment::class.java)
        startActivity(intent)
        finish()
    } */

    fun submitExpense(expense_description: String, expense_amount: Number, user: ParseUser) {
        //create the expense object
        val expense = Expense()
        expense.setDesc(expense_description)
        expense.setAmount(expense_amount)
        expense.setUser(user)

        //post.setImage(ParseFile(file))

        expense.saveInBackground { exception ->
            if (exception != null) {
                // Something has went wrong
                Log.e(TAG, "Error while saving expense")
                exception.printStackTrace()
                // TODO: Show a toast to tell user something went wrong with saving post
                Toast.makeText(this, "Something went wrong with saving the post!", Toast.LENGTH_SHORT).show()
            } else {
                Log.i(TAG, "Successfully saved expense")
                //TODO: Resetting the EditText field to be empty
                //view.findViewById<EditText>(R.id.description).text.clear()
                //TODO: Reset the ImageView to empty
                //view.findViewById<ImageView>(R.id.imageView).setImageDrawable(null)

            }
        }
    }

    companion object {
        const val TAG = "ComposeExpense"
    }
}