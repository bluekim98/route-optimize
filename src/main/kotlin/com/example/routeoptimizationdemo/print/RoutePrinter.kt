package com.example.routeoptimizationdemo.print

import com.example.routeoptimizationdemo.data.Location

class RoutePrinter {
    fun printGPS(route: List<Location>) {
        println("============== GPS coordinates ==============")
        route.forEach { location ->
            val str = "${location.lat},${location.lon},true"
            println(str)
        }
        println("=============================================")
    }
}