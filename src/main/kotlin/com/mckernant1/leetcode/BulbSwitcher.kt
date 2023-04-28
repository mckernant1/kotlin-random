package com.mckernant1.leetcode

/**
 * https://leetcode.com/problems/bulb-switcher/
 *
 * There are n bulbs that are initially off. You first turn on all the bulbs, then you turn off every second bulb.
 *
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
 *
 * Return the number of bulbs that are on after n rounds.
 *
 *
 *
 *
 */

fun main() {
    println(bulbSwitch(3))
    println(bulbSwitch(5))
    println(bulbSwitch(10))
    println(bulbSwitch(15))
    println(bulbSwitch(25))
}

fun bulbSwitch(n: Int): Int {
    if (n == 0) return 0

    var skipNum = 2
    var skipCounter = 0
    var counter = 1

    for (i in 2..n) {
        if (skipCounter == skipNum) {
            ++counter
            skipNum += 2
            skipCounter = 0
        } else {
            ++skipCounter
        }
    }

    return counter
}
