package com.example.finants

import com.parse.ParseObject
import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseUser
import java.time.temporal.TemporalAmount

//Goal : String
//Amount : String gets changed into number
//User : User
@ParseClassName("Post")
class Post : ParseObject() {

    fun getGoal(): String? {
        return getString(KEY_GOAL)
    }

    fun setGoal(goal: String) {
        put(KEY_GOAL, goal)
    }

    fun getAmount(): Number? {
        return getNumber(KEY_AMOUNT)
    }

    fun setAmount(amount: Number) {
        put(KEY_AMOUNT, amount)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }

    companion object {
        const val KEY_GOAL = "goal"
        const val KEY_AMOUNT = "amount"
        const val KEY_USER = "user"
    }
}