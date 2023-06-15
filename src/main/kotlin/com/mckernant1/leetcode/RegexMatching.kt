package com.mckernant1.leetcode


/**
 * ab*cd*
 * ab | cd | []
 * abbbbbbbbbcdddddd
 *
 *
 * a.|c|cc
 * ab.cccbbbcc
 *
 */
fun main() {
    println(isMatch("aaa", "ab*a*c*a"))
//    println(isMatch("aab", "c*a*b"))
}

private fun isMatch(input: String, pattern: String): Boolean {
    if (pattern == ".*") return true
    return if (input.length <= 1 && pattern.length <= 1) {
        println("Matching $input == $pattern")
        input == pattern || pattern == "."
    } else {
        println("$input == $pattern")
        return if (pattern.getOrNull(1) == '*') {
            isMatch(
                input.dropWhile { it == pattern[0] },
                pattern.dropWhile { it == pattern[0] || it == '*' }
            )
        } else {
            isMatch(input.drop(1), pattern.drop(1))
        }
    }
}


//fun chunkEq(input: String, pattern: String): Boolean {
//    for ((i, c) in input.withIndex()) {
//        val patternChar = pattern.getOrNull(i) ?: pattern.last()
//        if (patternChar != '.' && c != patternChar) {
//            return false
//        }
//    }
//}

