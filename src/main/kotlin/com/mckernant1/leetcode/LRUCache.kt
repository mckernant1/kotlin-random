package com.mckernant1.leetcode


fun main() {
    val l = LRUCache(5)
    l.setValue("yes", "yes")
    l.setValue("no", "no")
    l.setValue("hi", "hi")
    l.setValue("bye", "bye")
    l.setValue("wow", "wow")
    println(l)
    l.get("yes")
    l.setValue("woot", "woot")
    println(l)

}

private class LRUCache(private val maxSize: Int) {

    private val values = mutableMapOf<String, String>()
    private val lruMap = mutableMapOf<String, Int>()
    private var lruCounter = 0

    fun setValue(key: String, value: String) {
        if (values.size >= maxSize) {
            val keyToRemove = lruMap.entries.minByOrNull { it.value }!!.key
            lruMap.remove(keyToRemove)
            values.remove(keyToRemove)
        }
        values[key] = value
        lruMap[key] = lruCounter
        ++lruCounter
    }

    fun get(key: String): String? {
        val toReturn = values[key]
        if (toReturn != null) {
            lruMap[key] = lruCounter
            ++lruCounter
        }
        return toReturn
    }

    override fun toString(): String = values.toString()

}
