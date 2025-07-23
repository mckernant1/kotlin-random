package com.mckernant1.leetcode

fun main() {
    println()
}

fun isPalindrome(x: Int): Boolean {
    val xstr = x.toString()

    for (c in 0..xstr.lastIndex / 2) {
        if (xstr[c] != xstr[xstr.lastIndex - c]) return false
    }
    return true
}
