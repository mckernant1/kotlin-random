package com.mckernant1.leetcode


fun main() {
    println(maxArea(intArrayOf(1, 1)))
    println(maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)))
}


private fun maxArea(height: IntArray): Int {
    val end = height.size

    var max = 0
    var i = 0
    var j = end - 1

    while (j > i) {
        val test = (j - i) * Math.min(height[i], height[j])
        if (max < test) max = test
        if (height[i] < height[j]) ++i
        else --j
    }

    return max
}
