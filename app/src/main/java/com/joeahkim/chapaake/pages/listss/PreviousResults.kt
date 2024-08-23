package com.joeahkim.chapaake.pages.listss


import android.util.Log
import java.sql.Date
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class PreviousResults(
    var homeTeam: String,
    var awayTeam: String,
    var date: String,
    var prediction : String,
    var result: String
)

fun fetchPreviousResults(onDataFetched: (List<PreviousResults>) -> Unit) {
    val database = Firebase.database
    val ref = database.getReference("results")

    ref.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val resultsList = mutableListOf<PreviousResults>()
            for (resultSnapshot in snapshot.children) {
                val result = resultSnapshot.getValue(PreviousResults::class.java)
                result?.let {
                    resultsList.add(it)
                }
            }
            // Sort by date
            val sortedList = resultsList.sortedByDescending {
                LocalDate.parse(it.date, DateTimeFormatter.ofPattern("M/d/yy"))
            }
            onDataFetched(sortedList)
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle error
        }
    })
}