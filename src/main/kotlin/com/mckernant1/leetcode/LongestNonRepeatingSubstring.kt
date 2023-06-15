package com.mckernant1.leetcode


fun main() {
    println(lengthOfLongestSubstring("abcabcbb"))
    println(lengthOfLongestSubstring(" "))
}


private fun lengthOfLongestSubstring(s: String): Int {
    var l = 0
    var r = 1

    var longest = 0

    while (r <= s.length) {
        val subStr = s.substring(l, r)
        if (containsDuplicate(subStr)) {
            if (l == r) {
                ++r
            } else {
                ++l
            }
        } else {
            longest = Math.max(longest, subStr.length)
            ++r
        }
    }

    return longest
}

private fun containsDuplicate(s: String): Boolean {
    val set = mutableSetOf<Char>()
    for (c in s) {
        if (c in set) {
            return true
        }
        set.add(c)
    }
    return false
}
