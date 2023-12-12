package com.mckernant1.advent2023

import com.mckernant1.commons.extensions.collections.SortedMaps.firstEntry
import com.mckernant1.commons.extensions.collections.SortedMaps.lastEntry


fun main() {


    val cords1 = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent()

    println(
        cords1.split("\n").sumOf { trebuchetPart1(it) }
    )

    val cords2 = """
        two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen
    """.trimIndent()

    println(
        cords2.split("\n").sumOf {
            trebuchetPart2(it).also { println(it) }
        }
    )
}


fun trebuchetPart1(s: String): Int {
    val first = s.dropWhile { !it.isDigit() }.first()
    val last = s.dropLastWhile { !it.isDigit() }.last()

    return "$first$last".toInt()
}


fun trebuchetPart2(s: String): Int {

    val minMap = numberMap.keys.associate {
        s.indexOf(it) to numberMap[it]!!
    }.toSortedMap()

    val maxMap = numberMap.keys.associate {
        s.lastIndexOf(it) to numberMap[it]!!
    }.toSortedMap()

    minMap.remove(-1)
    maxMap.remove(-1)
    return "${minMap.firstEntry().value}${maxMap.lastEntry().value}".toInt()
}

val numberMap = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
    "1" to 1,
    "2" to 2,
    "3" to 3,
    "4" to 4,
    "5" to 5,
    "6" to 6,
    "7" to 7,
    "8" to 8,
    "9" to 9
)
