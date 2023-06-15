package com.mckernant1

import com.mckernant1.lol.esportsApi

fun main() {

    val t = esportsApi.getTournamentsForLeague("LCS")
        .maxByOrNull { it.startDate!! }!!

    esportsApi.getMatchesForTournament("LCS_2023_Spring").forEach { println(it) }

}
