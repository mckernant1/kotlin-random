package com.mckernant1.leetcode


/**
 * Interview Question from Hopper.
 *
 * ChatGPT generated solution
 */
fun main() {
    val flights = listOf(
        Flight("A1", "City1", "City2"),
        Flight("A2", "City2", "City3"),
        Flight("A3", "City3", "City4"),
        Flight("A4", "City4", "City5"),
        Flight("A5", "City2", "City5"),
        Flight("A6", "City1", "City3"),
        Flight("A7", "City3", "City5")
    )

    val startLocation = "City1"
    val endLocation = "City5"

    val allPaths = getAllPaths(flights, startLocation, endLocation)
    for (path in allPaths) {
        println("Route:")
        for (flight in path) {
            println("${flight.depart} to ${flight.arrival} (Flight ${flight.code})")
        }
        println()
    }
}

private data class Flight(
    val code: String,
    val depart: String,
    val arrival: String
)

private fun getAllPaths(
    paths: List<Flight>,
    start: String,
    end: String
): List<List<Flight>> {
    // Helper function to find all paths recursively
    fun findPaths(current: String, visited: Set<String>): List<List<Flight>> {
        // Base case: if the current location is the end, return a list with an empty path
        if (current == end) {
            return listOf(emptyList())
        }

        // Recursive case: find all possible flights from the current location
        val possibleFlights: List<Flight> = paths.filter { flight ->
            flight.depart == current && flight.arrival !in visited
        }

        // Recursive step: iterate through each possible flight and find paths from the arrival location
        val allPaths: MutableList<List<Flight>> = mutableListOf<List<Flight>>()
        for (flight: Flight in possibleFlights) {
            val updatedVisited: Set<String> = visited + flight.arrival
            val subPaths: List<List<Flight>> = findPaths(flight.arrival, updatedVisited)
            val extendedPaths: List<List<Flight>> = subPaths.map { subPath ->
                listOf(flight) + subPath
            }
            allPaths.addAll(extendedPaths)
        }

        return allPaths
    }

    return findPaths(start, setOf(start))
}

