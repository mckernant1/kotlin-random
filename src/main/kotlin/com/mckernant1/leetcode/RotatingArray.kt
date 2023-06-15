package com.mckernant1.leetcode


private fun main() {
    val r = RotatingArray(5)
    r.add(1)
    r.add(2)
    r.add(3)
    r.add(4)
    r.add(5)
    println(r)

    r.add(6)
    r.add(7)
    println(r)

    r.remove()
    r.add(8)
    r.add(9)

    println(r)
}

class RotatingArray(val size: Int) {

    private val arr = Array(size) { 0 }

     var start = 0
     var end = 0

    fun add(t: Int) {
        arr[end] = t

        if (end >= size - 1) {
            end = 0
        } else {
            ++end
        }
    }

    fun remove(): Int {
        val ret = arr[start]
        arr[start] = 0
        ++start
        return ret
    }

    override fun toString(): String = arr.toList().toString()
}
