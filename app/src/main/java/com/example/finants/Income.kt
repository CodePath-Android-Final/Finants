package com.example.finants

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser

//Desc : String
//Amount : String gets changed into number
//User : User
@ParseClassName("Income")
class Income : ParseObject() {

    fun getDesc(): String? {
        return getString(KEY_INCOME_DESCRIPTION)
    }

    fun setDesc(desc: String) {
        put(KEY_INCOME_DESCRIPTION, desc)
    }

    fun getAmount(): Number? {
        return getNumber(KEY_INCOME_AMOUNT)
    }

    fun setAmount(amount: Number) {
        put(KEY_INCOME_AMOUNT, amount)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }

    companion object {
        const val KEY_INCOME_DESCRIPTION = "income_desc"
        const val KEY_INCOME_AMOUNT = "income_amt"
        const val KEY_USER = "user"
    }
}