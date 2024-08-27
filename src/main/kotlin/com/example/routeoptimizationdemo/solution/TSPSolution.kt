package com.example.routeoptimizationdemo.solution

import com.example.routeoptimizationdemo.data.Location

interface TSPSolution {
    fun solve(route: List<Location>, matrix: Map<String, Map<String, Double>>): List<Location>
}