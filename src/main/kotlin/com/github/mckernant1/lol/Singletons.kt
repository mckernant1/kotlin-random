package com.github.mckernant1.lol

import com.github.mckernant1.aws.ddbClient
import com.github.mckernant1.lol.blitzcrank.models.PredictionsAccess
import com.github.mckernant1.lol.blitzcrank.models.UserSettingsAccess
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


internal val predictionAccess by lazy {
    PredictionsAccess(ddbClient)
}

internal val userSettingsAccess by lazy {
    UserSettingsAccess(ddbClient)
}
