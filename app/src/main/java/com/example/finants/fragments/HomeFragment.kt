package com.example.finants.fragments

import android.os.Bundle
import android.util.Log
import android.util.Log.ASSERT
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finants.*
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser
import java.time.LocalDate
import java.time.LocalTime

class HomeFragment : Fragment() {
    lateinit var userRecyclerView: RecyclerView

    lateinit var adapter: PostAdapter

    lateinit var totalSaved: TextView
    lateinit var monthlySavings: TextView
    lateinit var monthlyIncome: TextView
    lateinit var monthlyExpense: TextView



    var userPosts: MutableList<Post> = mutableListOf()
    var userExpenses: MutableList<Expense> = mutableListOf()
    var userIncomes: MutableList<Income> = mutableListOf()

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
        monthlySavings=view.findViewById(R.id.tvMonthlySavings)

        queryIncomeExpense()

        queryUserPosts()
    }

    private fun getMonthlyIncome(): Float {
        var total:Float
        total= 0F
        for(income in userIncomes){
            if((income.getDateCreated().month+1).toString()==LocalDate.now().monthValue.toString()){
                total+=income.getAmount()!!.toInt()
            }
        }
        return total
    }

    private fun getMonthlyExpense(): Float {
        var total:Float
        total= 0F
        for(expense in userExpenses){
            if((expense.getDateCreated().month+1).toString()==LocalDate.now().monthValue.toString()){
                total+=expense.getAmount()!!.toInt()
            }
        }
        return total
    }
//
    private fun getTotalSavings(): Float{
        var total:Float
        total= 0F
        for(income in userIncomes){
            total+=income.getAmount()!!.toInt()
        }
        return total
    }


    fun queryIncomeExpense(){
        val queryInc: ParseQuery<Income> = ParseQuery.getQuery(Income::class.java)
        queryInc.include(Income.KEY_USER)
        queryInc.whereEqualTo(Income.KEY_USER,ParseUser.getCurrentUser())
        queryInc.addDescendingOrder("createdAt")
        var monSaved:Float=0F
        queryInc.findInBackground(object: FindCallback<Income> {
            override fun done(incomes:MutableList<Income>?, e: ParseException?){
                if(e!=null){
                    Log.e(TAG,"Error fetching incomes")
                    Log.e(TAG,e.localizedMessage)
                }else{
                    if(incomes!=null){
                        for(income in incomes){
                            Log.i(HomeFragment.TAG, "Income: " + income.getAmount() + " , username: " + income.getUser()?.username)
                        }
                        userIncomes.addAll(incomes)
                        monthlyIncome.text="$"+getMonthlyIncome().toString()
                        totalSaved.text="$"+getTotalSavings().toString()
                        monSaved+=getMonthlyIncome()
                    }
                }
            }
        })
        val query: ParseQuery<Expense> = ParseQuery.getQuery(Expense::class.java)
        query.include(Expense.KEY_USER)
        query.whereEqualTo(Expense.KEY_USER,ParseUser.getCurrentUser())
        query.addDescendingOrder("createdAt")
        query.findInBackground(object: FindCallback<Expense> {
            override fun done(expenses:MutableList<Expense>?, e: ParseException?){
                if(e!=null){
                    Log.e(TAG,"Error fetching expenses")
                    Log.e(TAG,e.localizedMessage)
                }else{
                    if(expenses!=null){
                        for(expense in expenses){
                            Log.i(HomeFragment.TAG, "Expense: " + expense.getAmount() + " , username: " + expense.getUser()?.username)
                        }
                        userExpenses.addAll(expenses)
                        monthlyExpense.text="$"+getMonthlyExpense().toString()
                        monSaved-=getMonthlyExpense()
                        monthlySavings.text="$"+monSaved.toString()
                    }
                }
            }
        })

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
                            Log.i(HomeFragment.TAG, "Post: " + post.getGoal() + " , username: " + post.getUser()?.username)
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