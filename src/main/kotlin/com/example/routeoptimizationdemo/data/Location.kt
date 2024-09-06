package com.example.routeoptimizationdemo.data

data class Location(
    val id: String,
    val lat: Double,
    val lon: Double
) {
    companion object {
        fun empty() = Location("", 0.0, 0.0)
    }
}
