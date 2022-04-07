package com.example.finants.fragments

import android.annotation.SuppressLint
import android.icu.number.IntegerWidth
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finants.Post
import com.example.finants.PostAdapter
import com.example.finants.R
import com.example.finants.User
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser
import com.parse.ParseUser.getCurrentUser

class HomeFragment : Fragment() {
    lateinit var userRecyclerView: RecyclerView

    lateinit var adapter: PostAdapter

    lateinit var totalSaved: TextView
    lateinit var monthlySavings: TextView
    lateinit var monthlyIncome: TextView
    lateinit var monthlyExpense: TextView
    lateinit var monthlyRecurringFees: TextView

    var userPosts: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userRecyclerView=view.findViewById(R.id.rvUserPosts)
        adapter= PostAdapter(requireContext(),userPosts)
        userRecyclerView.adapter=adapter
        userRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        totalSaved=view.findViewById(R.id.tvTotalSaved)
        monthlyExpense=view.findViewById(R.id.tvMonthlyExpense)
        monthlyIncome=view.findViewById(R.id.tvMonthlyIncome)
        monthlyRecurringFees=view.findViewById(R.id.tvRecurringFees)
        monthlySavings=view.findViewById(R.id.tvMonthlySavings)
        //val user=User()

//        totalSaved.text=user.getTotalSavings().toString()
//        monthlyExpense.text=user.getMonthlyExpense().toString()
//        monthlyIncome.text=user.getMonthlyIncome().toString()
//        monthlyRecurringFees.text=user.getRecurringFees().toString()
//        monthlySavings.text=user.getMonthlySavings().toString()

        queryUserPosts()
    }

    fun queryUserPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.whereEqualTo(Post.KEY_USER,ParseUser.getCurrentUser())
        query.addDescendingOrder("createdAt")
        query.findInBackground(object: FindCallback<Post> {
            override fun done(posts:MutableList<Post>?, e: ParseException?){
                if(e!=null){
                    Log.e(TAG,"Error fetching posts")
                    Log.e(TAG,e.localizedMessage)
                }else{
                    if(posts!=null){
                        for(post in posts){
                            Log.i(FeedFragment.TAG, "Post: " + post.getGoal() + " , username: " + post.getUser()?.username)
                        }
                        userPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    companion object{
        const val TAG = "HomeFragment"
    }
}