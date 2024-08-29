package com.example.routeoptimizationdemo.solution

import com.example.routeoptimizationdemo.data.Location
import com.example.routeoptimizationdemo.data.TSPBestSolution

interface TSPSolution {
    fun solve(route: List<Location>, matrix: Map<String, Map<String, Double>>): TSPBestSolution
}