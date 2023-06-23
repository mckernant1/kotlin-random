package com.mckernant1.leetcode

import java.util.regex.Pattern

fun main() {
    println(translate("stop"))
    println(translate("end-to-end testing"))
    println(translate("no way, no how, not at all"))
}

val p = Pattern.compile("\\w+")
val vowels = setOf('a', 'e', 'i', 'o', 'u')

fun translate(s: String): String {
    val m = p.matcher(s)

    return m.replaceAll { result ->
        val str = result.group()
        val firstVowelAt = str.indexOfFirst { it in vowels }
        val prefix = str.substring(0, firstVowelAt)
        val stem = str.substring(firstVowelAt)
        "${stem}${prefix}ay"
    }
}
