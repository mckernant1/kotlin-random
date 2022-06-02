package com.github.mckernant1.lol.predictionsbot

import com.github.mckernant1.aws.ddbEnhanced
import kotlin.streams.asSequence
import kotlin.streams.toList
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional

@DynamoDbImmutable(builder = Prediction.Builder::class)
class Prediction(
    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute("discordId")
    @get:DynamoDbSecondarySortKey(indexNames = [MATCH_ID_INDEX_NAME])
    val userId: String,
    @get:DynamoDbAttribute("matchId")
    @get:DynamoDbSortKey
    @get:DynamoDbSecondaryPartitionKey(indexNames = [MATCH_ID_INDEX_NAME])
    val matchId: String,
    val prediction: String,
) {

    companion object {
        private const val MATCH_ID_INDEX_NAME = "games-by-match-id-index"
        private const val TABLE_NAME = "discord-predictions"

        private val table by lazy {
            ddbEnhanced.table(TABLE_NAME, TableSchema.fromImmutableClass(Prediction::class.java))
        }

        fun getItem(userId: String, matchId: String): Prediction? =
            table.getItem(Key.builder().partitionValue(userId).sortValue(matchId).build())

        fun putItem(prediction: Prediction) = table.putItem(prediction)

        fun scan(): Sequence<Prediction> = table.scan().items().asSequence()

        fun getAllPredictionsForMatch(matchId: String): Sequence<Prediction> =
            table.index(MATCH_ID_INDEX_NAME).query { it ->
                it.queryConditional(
                    QueryConditional.keyEqualTo(
                        Key.builder().partitionValue(matchId).build()
                    )
                )
            }.stream().flatMap { it.items().stream() }.asSequence()

    }
    class Builder {
        private var matchId: String? = null
        fun matchId(value: String) = apply { matchId = value }

        private var discordId: String? = null
        fun userId(value: String) = apply { discordId = value }

        private var prediction: String? = null
        fun prediction(value: String) = apply { prediction = value }

        fun build() = Prediction(
            matchId = matchId!!,
            userId = discordId!!,
            prediction = prediction!!
        )
    }
}
