package com.github.mckernant1.interviews


fun main() {
    println("${leastCoins(8, listOf(5, 4, 1))} should be {4=2, 1=0, 5=0}")
    println("${leastCoins(72, listOf(25, 10, 5, 1))} should be {10=2, 1=2, 5=0, 25=2}")
    println("${leastCoins(11, listOf(3, 1, 5))} should be {1=1, 3=0, 5=2}")
}


fun leastCoins(total: Int, dividers: List<Int>): MutableMap<Int, Int> {

    // Need to sort the array twice. First by the number to make sure that the dividers are coming in order
    // Second by the GCF to split the total by the biggest coin possible
    val sortedDividers = dividers
        .asSequence()
        .sorted()
        .sortedByDescending { it.gcf(total) }

    var shrinkingTotal = total
    // Group the coins into a Map<Coin,Counter> where coins are added based on GCF with total.
    // This is to avoid having to split coins into small parts later down the line
    val coinMap = mutableMapOf<Int, Int>()
    sortedDividers.forEach {
        val numberOfCoinToUse = shrinkingTotal / it
        shrinkingTotal -= it * numberOfCoinToUse
        coinMap[it] = numberOfCoinToUse
    }

    println("Partial coin map $coinMap")

    coinMap.forEach { (coin, count) ->
        // Get the dividers such that
        // - the divider is not 1
        // - the divider is not itself
        // - the divider and the coin have an LCM less than the number of coins times value of coin AND the coin is bigger than current coin
        // Get the biggest one
        val largerCoin = dividers
            .asSequence()
            .filterNot { it == 1 }
            .filterNot { it == coin }
            .filter { it.lcm(coin) < coin * count && coin < it }
            .maxByOrNull { it } ?: return@forEach

        // Get the number of each coin to add and remove and do it
        val numberLargerCoinToAdd = coin * count / largerCoin
        val numberSmallerCoinToRemove = (largerCoin * numberLargerCoinToAdd) / coin
        coinMap[coin] = coinMap[coin]!! - numberSmallerCoinToRemove
        coinMap[largerCoin] = coinMap[largerCoin]!! + numberLargerCoinToAdd
    }

    return coinMap
}


fun Int.gcf(other: Int): Int = when {
    this > other -> if (this % other == 0) other else other.gcf(this % other)
    this < other -> if (other % this == 0) this else this.gcf(other % this)
    else -> this
}

fun Int.lcm(other: Int) = (this * other) / this.gcf(other)
