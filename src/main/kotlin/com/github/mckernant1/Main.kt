package com.github.mckernant1

import com.github.mckernant1.aws.cwl.CloudWatchInsightsQueries

fun main() {
    println(CloudWatchInsightsQueries.stringSearch("ERROR", "FATAL"))
}
