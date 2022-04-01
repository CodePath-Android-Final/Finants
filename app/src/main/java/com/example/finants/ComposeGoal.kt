package com.example.finants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseFile
import com.parse.ParseUser
import java.io.File

class ComposeGoal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose_goal)

        findViewById<Button>(R.id.submitGoalBtn).setOnClickListener {
            val goalDescription = findViewById<EditText>(R.id.goalDesc).text.toString()
            val amountDescription = findViewById<EditText>(R.id.amountDesc).text.toString().toInt()
            val user = ParseUser.getCurrentUser()

            submitGoal(goalDescription, amountDescription, user)
        }
    }

    fun submitGoal(goal_description: String, amount_description: Number, user: ParseUser) {
        //create the post object
        val post = Post()
        post.setGoal(goal_description)
        post.setAmount(amount_description)
        post.setUser(user)
        //post.setImage(ParseFile(file))

        post.saveInBackground { exception ->
            if (exception != null) {
                // Something has went wrong
                Log.e(TAG, "Error while saving post")
                exception.printStackTrace()
                // TODO: Show a toast to tell user something went wrong with saving post
                Toast.makeText(this, "Something went wrong with saving the post!", Toast.LENGTH_SHORT).show()
            } else {
                Log.i(TAG, "Successfully saved post")
                //TODO: Resetting the EditText field to be empty
                //view.findViewById<EditText>(R.id.description).text.clear()
                //TODO: Reset the ImageView to empty
                //view.findViewById<ImageView>(R.id.imageView).setImageDrawable(null)

            }
        }
    }

    companion object {
        const val TAG = "ComposeGoal"
    }


}