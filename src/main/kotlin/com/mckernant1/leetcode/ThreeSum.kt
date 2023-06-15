package com.mckernant1.leetcode

fun main() {
//    println(threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)))
//    println(threeSum(intArrayOf(0, 1, 1)))
    println(threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)))
}

private fun threeSum(nums: IntArray): List<List<Int>> {
    val nums = nums.sorted()
    var middle = nums.size / 2
    var l = middle - 1
    var r = middle + 1
    var alt = true
    val triples = mutableListOf<List<Int>>()
    while (l >= 0 && r <= nums.size - 1) {
        println("Round")
        while (middle > l && middle < r) {
            println("$l $middle $r => ${nums[l] + nums[middle] + nums[r]}")
            if (nums[l] + nums[middle] + nums[r] == 0) {
                triples.add(listOf(nums[l], nums[middle], nums[r]))
            }
            ++middle
        }
        if (alt) {
            ++r
        } else {
            --l
        }
        alt = !alt
        middle = l + 1
    }

    return triples.distinct()
}
