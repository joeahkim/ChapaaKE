package com.joeahkim.chapaake

import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun convertKenyanTimeToLocal(kenyanTime: String): String {
    val kenyanLocalTime = LocalTime.parse(kenyanTime, DateTimeFormatter.ofPattern("HH:mm"))

    val currentDate = ZonedDateTime.now(ZoneId.of("Africa/Nairobi")).toLocalDate()

    val kenyanDateTime = kenyanLocalTime.atDate(currentDate)
    val kenyanZoneId = ZoneId.of("Africa/Nairobi")
    val kenyanZonedDateTime = ZonedDateTime.of(kenyanDateTime, kenyanZoneId)

    val userZoneId = ZoneId.systemDefault()

    val userZonedDateTime = kenyanZonedDateTime.withZoneSameInstant(userZoneId)

    val userFormatter = DateTimeFormatter.ofPattern("HH:mm")
    return userZonedDateTime.format(userFormatter)
}