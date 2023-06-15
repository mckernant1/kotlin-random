package com.mckernant1.leetcode

fun main() {
    println(permute(intArrayOf(1, 2, 3)))
}

private fun permute(numsInit: IntArray): List<List<Int>> {
    val nums = numsInit.toMutableList()
    while (nums.size < 6) {
        nums.add(-100)
    }

    val res = mutableListOf<List<Int>>()

    for (i in nums) {
        for (j in nums) {
            for (k in nums) {
                for (l in nums) {
                    for (m in nums) {
                        for (o in nums) {
                            res.add(listOf(i, j, k, l, m, o).distinct().filter { it != -100 })
                        }
                    }
                }
            }
        }
    }

    return res.filter { it.size == numsInit.size }.distinct()
}

