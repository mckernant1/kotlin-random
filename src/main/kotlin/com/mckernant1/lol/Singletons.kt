package com.mckernant1.lol

import com.github.mckernant1.lol.blitzcrank.models.PredictionsAccess
import com.github.mckernant1.lol.blitzcrank.models.UserSettingsAccess
import com.mckernant1.lol.esports.api.ApiClient
import com.mckernant1.lol.esports.api.client.DefaultApi
import okhttp3.OkHttpClient
import org.slf4j.LoggerFactory
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.time.Duration

internal val esportsApi = DefaultApi(
    ApiClient(
        OkHttpClient.Builder()
            .readTimeout(Duration.ofSeconds(60))
            .connectTimeout(Duration.ofSeconds(60))
            .writeTimeout(Duration.ofSeconds(60))
            .build()
    )
)

internal val ddbClient = DynamoDbClient
    .builder()
    .region(Region.US_WEST_2)
    .build()

internal val logger by lazy {
    LoggerFactory.getLogger("LolLogger")
}


internal val predictionAccess by lazy {
    PredictionsAccess(ddbClient)
}

internal val userSettingsAccess by lazy {
    UserSettingsAccess(ddbClient)
}
