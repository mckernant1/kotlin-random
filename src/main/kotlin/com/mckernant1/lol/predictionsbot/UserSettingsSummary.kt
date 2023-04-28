package com.mckernant1.lol.predictionsbot

import com.mckernant1.lol.userSettingsAccess
import java.util.TimeZone

fun main() {

    val settings = userSettingsAccess.scan()
        .toList()

    val settingsByTZ = settings
        .groupBy { it.timezone }
        .mapValues { (_, entries) -> entries.size }
        .mapKeys { (tz, _) -> TimeZone.getTimeZone(tz) }
        .toList()
        .sortedByDescending { (_, count) -> count }


    println("There are ${settings.size} user settings")
    settingsByTZ.forEach { (tz, count) ->
        println("${tz.displayName} has $count users")
    }

}
