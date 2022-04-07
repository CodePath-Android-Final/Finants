package com.example.finants

import android.icu.number.IntegerWidth
import com.parse.ParseObject
import com.parse.ParseClassName
import com.parse.ParseUser


@ParseClassName("User")
class User : ParseObject() {
    fun getTotalSavings(): Number? {
        return getNumber(KEY_TOTAL_SAVED)
    }

    fun setTotalSavings(amount: Number) {
        put(KEY_TOTAL_SAVED, amount)
    }

    fun getMonthlyIncome(): Number? {
        return getNumber(KEY_MONTHLY_INCOME)
    }

    fun setMonthlyIncome(amount: Number) {
        put(KEY_MONTHLY_INCOME, amount)
    }

    fun getMonthlyExpense(): Number? {
        return getNumber(KEY_MONTHLY_EXPENSE)
    }

    fun setMonthlyExpense(amount: Number) {
        put(KEY_MONTHLY_EXPENSE, amount)
    }

    fun getMonthlySavings(): Number? {
        return getNumber(KEY_MONTHLY_SAVINGS)
    }

    fun setMonthlySavings(amount: Number) {
        put(KEY_MONTHLY_SAVINGS, amount)
    }

    fun getRecurringFees(): Number? {
        return getNumber(KEY_RECURRING_FEES)
    }

    fun setRecurringFees(amount: Number) {
        put(KEY_RECURRING_FEES, amount)
    }

    companion object {
        const val KEY_TOTAL_SAVED="total_saved"
        const val KEY_MONTHLY_INCOME="monthly_income"
        const val KEY_MONTHLY_EXPENSE="monthly_expense"
        const val KEY_RECURRING_FEES="recurring_fees"
        const val KEY_MONTHLY_SAVINGS="monthly_savings"
    }
}