package com.github.mckernant1.discord

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder


internal val jda: JDA by lazy {
    JDABuilder
        .createDefault(System.getenv("BOT_TOKEN"))
        .build()
        .awaitReady()
}

