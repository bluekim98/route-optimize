package com.example.routeoptimizationdemo.distance

import com.example.routeoptimizationdemo.data.Location

class DistanceMatrixGenerator(
    private val calculator: DistanceCalculator,
) {
    fun generate(route: List<Location>): Map<String, Map<String, Double>> {
        val size = route.size
        val matrix = mutableMapOf<String, Map<String, Double>>()
        for (i in 0 until size) {
            for (j in 0 until size) {
                val start = route[i]
                val end = route[j]
                val currentMap = matrix[start.id]?.toMutableMap() ?: mutableMapOf()
                currentMap[end.id] = calculator.distance(start, end)
                matrix[start.id] = currentMap
            }
        }
        return matrix
    }
}