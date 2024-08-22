package com.joeahkim.chapaake.pages.listss


import java.sql.Date

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class PreviousResults(
    var homeTeam: String,
    var awayTeam: String,
    var date: String,
    var prediction : String,
    var result: String
)


fun getPreviousResults(): List<PreviousResults> {
    val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy")

    return listOf(
        PreviousResults("Arsenal", "Chelsea", "11/2/23", "Home", "Win"),
        PreviousResults("Manchester", "Chelsea", "11/2/23", "Home", "Win"),
        PreviousResults("City", "Chelsea", "11/2/24", "Home", "Loose"),
        PreviousResults("Tottenham", "Chelsea", "11/2/23", "Home", "Win"),
        PreviousResults("City", "Chelsea", "11/2/24", "Home", "Loose"),
        PreviousResults("City", "Chelsea", "11/2/24", "Home", "Loose"),
        PreviousResults("City", "Chelsea", "11/2/24", "Home", "Loose"),
        PreviousResults("City", "Chelsea", "11/2/22", "Home", "Loose"),
        PreviousResults("City", "Chelsea", "11/2/25", "Home", "Loose"),
        PreviousResults("City", "Chelsea", "11/2/26", "Home", "Loose"),
        PreviousResults("City", "Chelsea", "11/2/26", "Home", "Loose"),
        PreviousResults("City", "Chelsea", "11/2/22", "Home", "Loose"),
    ).sortedByDescending {
        LocalDate.parse(it.date, dateFormatter)
    }
}
