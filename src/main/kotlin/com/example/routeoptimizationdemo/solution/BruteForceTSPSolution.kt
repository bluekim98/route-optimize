package com.example.routeoptimizationdemo.solution

import com.example.routeoptimizationdemo.data.Location

class BruteForceTSPSolution : TSPSolution {
    override fun solve(route: List<Location>, matrix: Map<String, Map<String, Double>>): List<Location> {
        val permutations = recursion(route)
        var minDistance = Double.MAX_VALUE
        var bestRoute = emptyList<Location>()

        for (permutation in permutations) {
            val distance = calculateDistance(permutation, matrix)

            if (distance < minDistance) {
                minDistance = distance
                bestRoute = permutation
            }
        }

        return bestRoute
    }

    private fun recursion(route: List<Location>): List<List<Location>> {
        if (route.size == 1) {
            return listOf(route)
        }

        val element = route.first()
        val permutations = recursion(route.drop(1))
        val result = mutableListOf<List<Location>>()

        for (permutation in permutations) {
            for (i in 0..permutation.size) {
                val newPermutation = permutation.toMutableList()
                newPermutation.add(i, element)
                result.add(newPermutation)
            }
        }

        println(result.size)

        return result
    }

    private fun calculateDistance(route: List<Location>, matrix: Map<String, Map<String, Double>>): Double {
        var distance = 0.0

        for (i in route.indices) {
            if (i == route.size - 1) {
                break
            }

            val start = route[i]
            val end = route[i + 1]

            distance += matrix[start.id]!![end.id]!!
        }

        return distance
    }
}