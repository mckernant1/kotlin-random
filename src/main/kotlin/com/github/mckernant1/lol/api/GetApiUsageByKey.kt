package com.github.mckernant1.lol.api

import com.github.mckernant1.aws.cwl.CloudWatchLogInsightsFetcher
import kotlinx.coroutines.runBlocking
import java.time.Instant
import java.time.temporal.ChronoUnit

fun main(): Unit = runBlocking {

    val fetcher = CloudWatchLogInsightsFetcher()

    fetcher.fetchRaw(
        logGroupName = "API-Gateway-Execution-Logs_t0zirqrkm7/Prod",
        queryString = """
            fields @timestamp, @message
            | sort @timestamp desc
            | filter @message like "Verifying Usage Plan for request:"
            | parse @message "API Key: * API Stage" as apiKey 
            | stats count() as keyCount by apiKey
        """.trimIndent(),
        startTime = Instant.now().minus(7, ChronoUnit.DAYS),
        endTime = Instant.now(),
        limit = 1000
    ).mapNotNull {
        ApiKeyCount(
            it.find { it.field() == "apiKey" }?.value() ?: return@mapNotNull null,
            it.find { it.field() == "keyCount" }?.value()?.toIntOrNull() ?: return@mapNotNull null
        )
    }.forEach {
        println("${it.apiKey} - ${it.count}")
    }

}


data class ApiKeyCount(
    val apiKey: String,
    val count: Int
)
