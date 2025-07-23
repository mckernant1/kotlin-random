package com.mckernant1.leetcode

import java.math.BigInteger


class ListNode(var `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        var cur: ListNode? = this
        var str = ""
        while (cur != null) {
            str += cur.`val`
            cur = cur.next
        }
        return str
    }
}

fun main() {
    println(toListNode(BigInteger.valueOf(342)))
    println(toNumber(addTwoNumbers(toListNode(BigInteger.valueOf(342)), toListNode(BigInteger.valueOf(465)))))
    println(toNumber(addTwoNumbers(toListNode(BigInteger.valueOf(9999999)), toListNode(BigInteger.valueOf(9999)))))
}

fun toNumber(l: ListNode?): BigInteger {
    var cur = l
    var str = ""
    while (cur != null) {
        str += cur.`val`
        cur = cur.next
    }
    return str.reversed().toBigInteger()
}

fun toListNode(i: BigInteger): ListNode? {
    val istr = i.toString().reversed()
    var curr = ListNode(istr[0].digitToInt())
    val start = curr
    for (c in 1..istr.lastIndex) {
        curr.next = ListNode(istr[c].digitToInt())
        curr = curr.next!!
    }
    return start
}

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val n1 = toNumber(l1)
    val n2 = toNumber(l2)

    return toListNode(n1 + n2)
}
