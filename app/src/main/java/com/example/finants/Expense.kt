package com.example.finants

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser
import java.util.*

//Desc : String
//Amount : String gets changed into number
//User : User
@ParseClassName("Expense")
class Expense : ParseObject() {

    fun getDesc(): String? {
        return getString(KEY_EXPENSE_DESCRIPTION)
    }

    fun setDesc(desc: String) {
        put(KEY_EXPENSE_DESCRIPTION, desc)
    }

    fun getAmount(): Number? {
        return getNumber(KEY_EXPENSE_AMOUNT)
    }

    fun setAmount(amount: Number) {
        put(KEY_EXPENSE_AMOUNT, amount)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }

    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }

    fun getDateCreated(): Date {
        return getCreatedAt()
    }

    companion object {
        const val KEY_EXPENSE_DESCRIPTION = "expense_desc"
        const val KEY_EXPENSE_AMOUNT = "expense_amt"
        const val KEY_USER = "user"
    }
}