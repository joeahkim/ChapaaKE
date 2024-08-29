package com.joeahkim.chapaake.pages.listss

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

fun fetchBetslips(onDataFetched: (List<Betslip>) -> Unit) {
    val database = Firebase.database
    val ref = database.getReference("betslips")

    ref.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val betslipsList = mutableListOf<Betslip>()
            for (betslipSnapshot in snapshot.children) {
                val betslip = betslipSnapshot.getValue(Betslip::class.java)
                betslip?.let {
                    betslipsList.add(it)
                }
            }
            onDataFetched(betslipsList)
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle error
        }
    })
}
