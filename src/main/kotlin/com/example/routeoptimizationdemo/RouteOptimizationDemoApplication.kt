package com.example.routeoptimizationdemo

import com.example.routeoptimizationdemo.distance.DistanceCalculator
import com.example.routeoptimizationdemo.distance.DistanceMatrixGenerator
import com.example.routeoptimizationdemo.distance.EuclideanDistanceCalculator
import com.example.routeoptimizationdemo.mock.RouteMock
import com.example.routeoptimizationdemo.print.RoutePrinter
import com.example.routeoptimizationdemo.solution.BruteForceTSPSolution
import com.example.routeoptimizationdemo.solution.TSPSolution

class RouteOptimizationDemoApplication

fun main() {
    val calculator: DistanceCalculator = EuclideanDistanceCalculator()
    val distanceMatrixGenerator = DistanceMatrixGenerator(calculator)
    val solution: TSPSolution = BruteForceTSPSolution()
    val printer = RoutePrinter()

    val route = RouteMock.D102
    val matrix = distanceMatrixGenerator.generate(route)

    val best = solution.solve(route, matrix)

    printer.printGPS(best)
}
