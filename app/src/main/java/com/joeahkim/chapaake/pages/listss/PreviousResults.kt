package com.joeahkim.chapaake.pages.listss

import java.sql.Date

data class PreviousResults(
    var homeTeam: String,
    var awayTeam: String,
    var date: String,
    var prediction : String,
    var result: String
)

fun getPreviousResults(): List<PreviousResults>{
    return listOf(
        PreviousResults("Arsenal","Chelsea","11/2/23","Home","Win"),
        PreviousResults("Manchester","Chelsea","11/2/23","Home","Win"),
        PreviousResults("City","Chelsea","11/2/24","Home","Loose"),
        PreviousResults("Tottenham","Chelsea","11/2/23","Home","Win")

    )
}