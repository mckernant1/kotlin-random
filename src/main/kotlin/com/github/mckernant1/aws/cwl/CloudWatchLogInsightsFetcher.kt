package com.github.mckernant1.aws.cwl

import com.github.mckernant1.standalone.delay
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient
import software.amazon.awssdk.services.cloudwatchlogs.model.ResultField
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

class CloudWatchLogInsightsFetcher(
    private val cwl: CloudWatchLogsClient = CloudWatchLogsClient.create()
) {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CloudWatchLogInsightsFetcher::class.java)
    }

    suspend fun fetch(
        logGroupName: String,
        queryString: String,
        startTime: Instant = Instant.now().minus(1, ChronoUnit.HOURS),
        endTime: Instant = Instant.now(),
        limit: Int = 20,
    ): Sequence<String> {
        return fetchRaw(logGroupName, queryString, startTime, endTime, limit)
            .asSequence()
            .flatten()
            .filter { it.field() == "@message" }
            .map { it.value() }
    }

    suspend fun fetchRaw(
        logGroupName: String,
        queryString: String,
        startTime: Instant = Instant.now().minus(1, ChronoUnit.HOURS),
        endTime: Instant = Instant.now(),
        limit: Int = 20,
    ): List<List<ResultField>> {
        val query = cwl.startQuery {
            it.logGroupName(logGroupName)
            it.limit(limit)
            it.startTime(startTime.epochSecond)
            it.endTime(endTime.epochSecond)
            it.queryString(queryString)
        }

        logger.info("""Starting Query with params
logGroupName: $logGroupName
startTime: $startTime
endTime: $endTime
limit: $limit
query: 
$queryString
        """.trimIndent())
        println()
        println()

        do {
            val describe = cwl.getQueryResults {
                it.queryId(query.queryId())
            }
            delay(Duration.ofSeconds(3))
            logger.info("${query.queryId()} has status ${describe.status()}")
        } while (!describe.isQueryFinished())
        return cwl.getQueryResults { it.queryId(query.queryId()) }.results()
    }

}
