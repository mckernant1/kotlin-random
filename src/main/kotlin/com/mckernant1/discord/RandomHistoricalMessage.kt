package com.mckernant1.discord


import com.mckernant1.commons.extensions.time.DurationFormat.format
import com.mckernant1.commons.standalone.measureOperation
import kotlinx.coroutines.runBlocking
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel
import java.time.*
import java.time.temporal.ChronoUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random
import kotlin.system.exitProcess


fun getRandomMessages(
    channel: MessageChannel,
    randomDay: Instant
): List<Message> {
    val randomNextDay = randomDay + Duration.ofDays(1)

    val (duration, out) = measureOperation {
        println("Looking through message history...")
        val counter = AtomicInteger(0)
        val messages = channel.iterableHistory
            .reverse()
            .asSequence()
            .dropWhile {
                counter.incrementAndGet()
                it.timeCreated.isBefore(randomDay.atOffset(ZoneOffset.UTC))
            }.takeWhile {
                it.timeCreated.isBefore(randomNextDay.atOffset(ZoneOffset.UTC))
            }
            .toList()

        Pair(messages, counter)
    }

    val (messages, counter) = out

    println("Finished getting messages. Took ${duration.format()}. Found ${messages.size} messages. Checked $counter items.")

    return messages

}


fun main(): Unit = runBlocking {
    val guild = jda.getGuildById("367508092994191361")
        ?: throw RuntimeException("Could not load guild")
    val channel = guild.getTextChannelById("1098823902899212298")
        ?: throw RuntimeException("Could not find a channel")

    val start = LocalDateTime.of(2020, 1, 1, 0, 0, 0)
        .toInstant(ZoneOffset.UTC)
    val end = Instant.now() - Duration.ofDays(365)

    var messages = emptyList<Message>()
    var randomDay = Instant.ofEpochSecond(
        Random.nextLong(start.epochSecond, end.epochSecond)
    ).truncatedTo(ChronoUnit.DAYS)

    while (messages.isEmpty()) {
        randomDay = Instant.ofEpochSecond(
            Random.nextLong(start.epochSecond, end.epochSecond)
        ).truncatedTo(ChronoUnit.DAYS)
        println("Trying random day $randomDay looking for messages.")
        messages = getRandomMessages(channel, randomDay)
    }

    val randomMessage = messages.randomOrNull()

    println(
        """
        On ${LocalDate.ofInstant(randomDay, ZoneOffset.UTC)} there were ${messages.size} messages sent
        Who sent the following message:
        "${randomMessage?.contentRaw}"

        Spoilers: ||${randomMessage?.author?.name}||
        Link: ||${randomMessage?.jumpUrl}||
    """.trimIndent()
    )

    exitProcess(0)
}
