package com.joeahkim.chapaake.pages.listss

data class Betslip(
    val accumulatorName: String = "",
    val tips: Map<String, TodaysTip> = emptyMap()  // Using Map to hold tips
)