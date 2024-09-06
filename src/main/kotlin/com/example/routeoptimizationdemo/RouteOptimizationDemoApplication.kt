package com.example.routeoptimizationdemo

import com.example.routeoptimizationdemo.distance.DistanceCalculator
import com.example.routeoptimizationdemo.distance.DistanceMatrixGenerator
import com.example.routeoptimizationdemo.distance.EuclideanDistanceCalculator
import com.example.routeoptimizationdemo.mock.RouteMock
import com.example.routeoptimizationdemo.print.RoutePrinter
import com.example.routeoptimizationdemo.solution.BruteForceTSPSolution
import com.example.routeoptimizationdemo.solution.DynamicProgrammingTSPSolution
import com.example.routeoptimizationdemo.solution.GeneticAlgorithmTSPSolution
import com.example.routeoptimizationdemo.solution.TSPSolution

class RouteOptimizationDemoApplication

fun main() {
    val calculator: DistanceCalculator = EuclideanDistanceCalculator()
    val distanceMatrixGenerator = DistanceMatrixGenerator(calculator)

    val route = RouteMock.D110
    val matrix = distanceMatrixGenerator.generate(route)

    val solution: TSPSolution = GeneticAlgorithmTSPSolution()
//    val solution: TSPSolution = DynamicProgrammingTSPSolution()
//    val solution: TSPSolution = BruteForceTSPSolution()

    val best = solution.solve(route, matrix)

    RoutePrinter().printTSPBest(best)
}