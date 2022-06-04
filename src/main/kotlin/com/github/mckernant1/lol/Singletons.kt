package com.github.mckernant1.lol

import com.github.mckernant1.lol.esports.api.ApiClient
import com.github.mckernant1.lol.esports.api.client.DefaultApi
import okhttp3.OkHttpClient
import org.slf4j.LoggerFactory
import java.time.Duration

internal val esportsApi = DefaultApi(
    ApiClient(
        OkHttpClient.Builder()
            .readTimeout(Duration.ofSeconds(60))
            .connectTimeout(Duration.ofSeconds(60))
            .writeTimeout(Duration.ofSeconds(60))
            .build()
    ).apply {
        setApiKey("CO6gm83RDj3U7LW2uFqKx41n0S834zFi4V7o2fKL")
    }
)

internal val logger by lazy {
    LoggerFactory.getLogger("LolLogger")
}
