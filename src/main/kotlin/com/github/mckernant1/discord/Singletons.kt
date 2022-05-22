package com.github.mckernant1.discord

import com.github.mckernant1.standalone.assertEnvironmentVariablesExist
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder


internal val jda: JDA by lazy {
    assertEnvironmentVariablesExist("BOT_TOKEN")
    JDABuilder
        .createDefault(System.getenv("BOT_TOKEN"))
        .build()
        .awaitReady()
}

