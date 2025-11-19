package com.mckernant1.leetcode



fun main() {
    println(letterCombinations("23"))
    println(letterCombinations("2"))
}

fun letterCombinations(digits: String): List<String> {

    val numbersMap = mapOf(
        2 to "abc",
        3 to "def",
        4 to "ghi",
        5 to "jkl",
        6 to "mno",
        7 to "pqrs",
        8 to "tuv",
        9 to "wxyz",
    )
    val l = mutableListOf<String>()

    for (digit in digits) {
        val mini = mutableListOf<Char>()
        for (c in numbersMap[digit.digitToInt()]!!) {
            mini.add(c)
        }
        println("mini: $mini")
        l.add(mini.joinToString())
    }

    return l
}




