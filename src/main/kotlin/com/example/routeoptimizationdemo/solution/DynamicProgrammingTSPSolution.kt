package com.example.routeoptimizationdemo.solution

import com.example.routeoptimizationdemo.data.Location
import com.example.routeoptimizationdemo.data.TSPBestSolution

class DynamicProgrammingTSPSolution : TSPSolution {
    private var N = 0
    private var dp: Array<DoubleArray> = emptyArray()
    private var route: List<Location> = emptyList()
    private var matrix: Map<String, Map<String, Double>> = emptyMap()
    private var parent: Array<IntArray> = emptyArray()

    override fun solve(route: List<Location>, matrix: Map<String, Map<String, Double>>): TSPBestSolution {
        this.N = route.size
        this.route = route
        this.matrix = matrix

        var overallMinCost = Double.MAX_VALUE
        var overallBestRoute = emptyList<Location>()

        for (start in 0 until N) {
            this.dp = Array(1 shl N) { DoubleArray(N) { -1.0 } }
            this.parent = Array(1 shl N) { IntArray(N) { -1 } }

            val bestCost = tsp(1 shl start, start)

            if (bestCost < overallMinCost) {
                overallMinCost = bestCost
                overallBestRoute = findRoute(start)
            }
        }

        return TSPBestSolution(overallBestRoute, overallMinCost)
    }

    private fun tsp(visited: Int, current: Int): Double {
        if (visited == (1 shl N) - 1) {
            return 0.0
        }

        if (dp[visited][current] != -1.0) {
            return dp[visited][current]
        }

        var minCost = Double.MAX_VALUE

        for (next in 0 until N) {
            if (visited and (1 shl next) != 0) continue

            val currentLocation = route[current]
            val nextLocation = route[next]
            val newCost = matrix[currentLocation.id]!![nextLocation.id]!! + tsp(visited or (1 shl next), next)
            if (newCost < minCost) {
                minCost = newCost
                parent[visited][current] = next
            }
        }

        dp[visited][current] = minCost

        return minCost
    }

    private fun findRoute(start: Int): List<Location> {
        val route = mutableListOf(start)
        var visited = 1 shl start
        var current = start

        while (parent[visited][current] != -1) {
            val next = parent[visited][current]
            route.add(next)
            visited = visited or (1 shl next)
            current = next
        }

        return route.map { this.route[it] }.reversed()
    }
}