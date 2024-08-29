package com.example.routeoptimizationdemo.print

import com.example.routeoptimizationdemo.data.TSPBestSolution

class RoutePrinter {
    fun printTSPBest(best: TSPBestSolution) {
        println("============== Best cost ==============")
        println("distance: ${best.distance}")
        println("============== GPS coordinates ==============")
        best.route.forEach { location ->
            val str = "${location.lat},${location.lon},true,${location.id}"
            println(str)
        }
        println("=============================================")
    }
}