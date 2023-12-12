package com.mckernant1.lol.predictionsbot

import com.mckernant1.aws.cwl.CloudWatchInsightsQueries
import com.mckernant1.aws.cwl.CloudWatchLogInsightsFetcher
import kotlinx.coroutines.runBlocking
import java.time.Instant
import java.time.temporal.ChronoUnit

fun main(): Unit = runBlocking {
    val fetcher = CloudWatchLogInsightsFetcher()

    fetcher.fetch(
        "/ecs/lol-predictions-bot",
        CloudWatchInsightsQueries.regexSearch(Regex("ERROR")),
        startTime = Instant.now().minus(7, ChronoUnit.DAYS),
        endTime = Instant.now(),
        limit = 100
    ).forEach {
        println(it)
    }
}
