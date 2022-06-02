package com.github.mckernant1.aws

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.services.dynamodb.DynamoDbClient

internal val ddbClient by lazy {
    DynamoDbClient.create()
}

internal val ddbEnhanced by lazy {
    DynamoDbEnhancedClient.create()
}
