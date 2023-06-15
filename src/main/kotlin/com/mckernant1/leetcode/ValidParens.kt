package com.mckernant1.leetcode

import java.util.Stack

fun main() {
    println(isValid("{}()"))
    println(isValid("()[]{}"))
    println(isValid("(]"))
}

private fun isValid(str: String): Boolean {
    val s = Stack<Char>()
    val openers = listOf('(', '[', '{')

    for (c in str) {
        when {
            c in openers -> s.push(c)
            s.isEmpty() -> return false
            c == ')' -> if (s.pop() != '(') return false
            c == '}' -> if (s.pop() != '{') return false
            c == ']' -> if (s.pop() != '[') return false
        }
    }
    return s.isEmpty()
}

