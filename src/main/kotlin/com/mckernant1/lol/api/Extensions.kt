package com.mckernant1.lol.api

import com.github.mckernant1.lol.esports.api.models.Match
import com.github.mckernant1.lol.esports.api.models.Tournament
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date

fun List<Match>.filterPastMatches() = filter {
    it.startTimeAsInstant().isBefore(Instant.now())
}

fun List<Match>.filterFutureMatches() = filter {
    it.startTimeAsInstant().isAfter(Instant.now())
}

fun Tournament.startDateAsDate(): Date? {
    return try {
        SimpleDateFormat("yyyy-MM-dd").parse(this.startDate)
    } catch (e: Exception) {
        null
    }
}

fun Tournament.endDateAsDate(): Date? {
    return try {
        SimpleDateFormat("yyyy-MM-dd").parse(this.endDate)
    } catch (e: Exception) {
        null
    }
}

fun Match.startTimeAsInstant(): Instant =
    Instant.ofEpochMilli(this.startTime.longValueExact())


fun Match.getLoser(): String = when (winner) {
    blueTeamId -> redTeamId
    redTeamId -> blueTeamId
    else -> "TBD"
}
