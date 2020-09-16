package com.fionicholas.footballapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

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