package com.example.routeoptimizationdemo.distance

import com.example.routeoptimizationdemo.data.Location

interface DistanceCalculator {
    fun distance(start: Location, end: Location): Double
}