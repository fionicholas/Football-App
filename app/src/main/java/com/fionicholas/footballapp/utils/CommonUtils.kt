package com.fionicholas.footballapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun AppCompatActivity.replaceFragment(viewRes: Int, fragment: Fragment, addToBackStack: Boolean) {
    val transaction = this.supportFragmentManager.beginTransaction()

    // Replace whatever is in the fragment_container view with this fragment,
    // and add the transaction to the back stack so the user can navigate back
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }

    transaction.replace(viewRes, fragment)

    // Commit the transaction
    transaction.commit()
}

fun String.toBaseDateFormat() : String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    var myDate: Date? = null
    try {
        myDate = dateFormat.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    val newFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return newFormat.format(myDate)
}