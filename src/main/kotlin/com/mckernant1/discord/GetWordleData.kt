package com.mckernant1.discord

import kotlin.system.exitProcess
import net.dv8tion.jda.api.entities.MessageHistory

fun main() {

    val wordleChannel = jda
        .getGuildById("367508092994191361")
        ?.getTextChannelById("945443235575255061")
        ?: error("Unable to get channel or server")


    val wordleRegex = "Wordle (?<wordleNumber>\\d{0,3}) (?<tries>\\w)\\/6".toRegex()
    MessageHistory.getHistoryFromBeginning(wordleChannel).complete()
    val allMessages = wordleChannel.iterableHistory.toList()
    println("All messagesSize: ${allMessages.size}")
    val mapValues: Map<String, List<Int>> = allMessages
        .filter { it.contentRaw.contains(wordleRegex) }
        .groupBy { it.author.name }
        .mapValues { (_, message) ->
            message.map { wordleRegex.find(it.contentRaw)?.groups?.get("tries")?.value?.toIntOrNull() ?: 7 }
        }

    mapValues
        .entries
        .sortedBy { it.value.average() }
        .forEach { (user, scores) ->
            println("$user has average score of ${scores.average()} out of ${scores.size} wordles")
        }
    exitProcess(0)
}
