package com.example.routeoptimizationdemo.distance

import com.example.routeoptimizationdemo.data.Location
import kotlin.math.pow
import kotlin.math.sqrt

class EuclideanDistanceCalculator : DistanceCalculator {
    override fun distance(start: Location, end: Location): Double {
        val dx = end.lat - start.lat
        val dy = end.lon - start.lon
        return sqrt(dx.pow(2) + dy.pow(2))
    }
}