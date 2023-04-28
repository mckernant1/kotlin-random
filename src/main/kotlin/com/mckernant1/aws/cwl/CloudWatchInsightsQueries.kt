package com.mckernant1.aws.cwl

object CloudWatchInsightsQueries {

    fun stringSearch(vararg terms: String): String = """
        fields @timestamp, @message
        | sort @timestamp desc
        ${terms.joinToString("\n") { "| filter @message like \"$it\"" }}
    """.trimIndent()

    fun regexSearch(regex: Regex): String = """
        fields @timestamp, @message
        | sort @timestamp desc
        | filter @message like /$regex/
    """.trimIndent()
}
