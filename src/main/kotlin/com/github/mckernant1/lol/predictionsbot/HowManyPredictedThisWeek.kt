package com.github.mckernant1.lol.predictionsbot

import com.github.mckernant1.lol.api.startTimeAsInstant
import com.github.mckernant1.lol.esportsApi
import com.github.mckernant1.lol.logger
import java.time.Duration
import java.time.Instant

fun main() {
    val distinctUsers = esportsApi.ongoingTournanments
        .asSequence()
        .flatMap {
            try {
                esportsApi.getMatchesForTournament(it.tournamentId)
            } catch (e: Exception) {
                logger.error("Hit Error getting matches for Tourny", e)
                emptyList()
            }
        }
        .filter { it.startTimeAsInstant() > Instant.now() - Duration.ofDays(7) }
        .flatMap { Prediction.getAllPredictionsForMatch(it.matchId) }
        .groupBy { it.userId }
        .mapValues { (_, predictions) -> predictions.count() }
        .toList()
        .sortedByDescending { (_, predictionsCount) -> predictionsCount }

    println("There are ${distinctUsers.size} distinct users predicted in the past week")
    println("UserId - PredictionsCount")
    distinctUsers.forEach { (user, predictionsCount) ->
        println("$user - $predictionsCount")
    }

}

