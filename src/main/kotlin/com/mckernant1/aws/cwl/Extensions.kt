package com.mckernant1.aws.cwl

import software.amazon.awssdk.services.cloudwatchlogs.model.GetQueryResultsResponse
import software.amazon.awssdk.services.cloudwatchlogs.model.QueryStatus
import software.amazon.awssdk.services.cloudwatchlogs.model.ResultField

fun GetQueryResultsResponse.isQueryFinished(): Boolean = when (this.status()) {
    QueryStatus.SCHEDULED -> false
    QueryStatus.RUNNING -> false
    QueryStatus.COMPLETE -> true
    QueryStatus.FAILED -> true
    QueryStatus.CANCELLED -> true
    QueryStatus.TIMEOUT -> true
    QueryStatus.UNKNOWN -> throw Exception("Unknown status for Query")
    QueryStatus.UNKNOWN_TO_SDK_VERSION -> throw Exception("Unknown status for Query")
    else -> throw Exception("Null status for Query")
}

fun List<ResultField>.toMap(): Map<String, String> = this.associate { it.field() to it.value() }

operator fun List<ResultField>.get(key: String): ResultField? = this.find { it.field() == key }
