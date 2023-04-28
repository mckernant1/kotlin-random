package com.mckernant1.leetcode


fun main() {
    println(intToRoman(3))
    println(intToRoman(58))
    println(intToRoman(1994))
}

fun intToRoman(num: Int): String {

    var s = ""
    val thousands = num / 1000
    var remain = num - (thousands * 1000)
    println("thousands: $thousands remain: $remain")
    s += (1..thousands).joinToString("") { "M" }

    val hundreds = remain / 100
    remain = remain - (hundreds * 100)
    s += when (hundreds) {
        9 -> "CM"
        4 -> "CD"
        in 0..3 -> (1..hundreds).joinToString("") { "C" }
        in 5..8 -> "D" + (1..(hundreds - 5)).joinToString("") { "C" }
        else -> error("hundreds $hundreds")
    }

    val tens = remain / 10
    remain = remain - (tens * 10)
    s += when (tens) {
        9 -> "XC"
        4 -> "XL"
        in 0..3 -> (1..tens).joinToString("") { "X" }
        in 5..8 -> "L" + (1..(tens - 5)).joinToString("") { "X" }
        else -> error("tens $tens")
    }

    val ones = remain
    s += when (ones) {
        9 -> "IX"
        4 -> "IV"
        in 0..3 -> (1..ones).joinToString("") { "I" }
        in 5..8 -> "V" + (1..(ones - 5)).joinToString("") { "I" }
        else -> error("ones $ones")
    }

    return s
}

fun getDigitAtPosition(num: Int, position: Int) {


}
