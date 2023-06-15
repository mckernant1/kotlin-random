package com.mckernant1.leetcode

import java.lang.StringBuilder

fun main() {
    println(mergeAlternately("abcd", "pq"))
}


private fun mergeAlternately(word1: String, word2: String): String {
    var w1Ctr = 0
    var w2Ctr = 0
    var appendable = StringBuilder()

    while (w1Ctr < word1.length && w2Ctr < word2.length) {
        appendable.append("${word1[w1Ctr]}${word2[w2Ctr]}")
        ++w1Ctr
        ++w2Ctr
    }

    while (w1Ctr < word1.length) {
        appendable.append("${word1[w1Ctr]}")
        ++w1Ctr
    }

    while (w2Ctr < word2.length) {
        appendable.append("${word2[w2Ctr]}")
        ++w2Ctr
    }

    return appendable.toString()
}
