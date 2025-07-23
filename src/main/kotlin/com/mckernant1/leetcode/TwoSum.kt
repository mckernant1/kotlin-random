package com.mckernant1.leetcode



fun main() {
    println(twoSum(intArrayOf(2,7,11,15), 9).toList())
    println(twoSum(intArrayOf(3,2,4), 6).toList())
    println(twoSum(intArrayOf(3,3), 6).toList())
}


fun twoSum(nums: IntArray, target: Int): IntArray {
    for (i in 0..nums.lastIndex) {
        for (j in i + 1..nums.lastIndex) {
            if (nums[i] + nums[j] == target) {
                return intArrayOf(i, j)
            }
        }
    }
    throw RuntimeException("Error")
}
