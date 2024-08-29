package com.example.routeoptimizationdemo.solution

import com.example.routeoptimizationdemo.data.Location
import com.example.routeoptimizationdemo.data.TSPBestSolution

class GeneticAlgorithmTSPSolution : TSPSolution {
    private val N = 0
    private val populationSize = 100
    private val generations = 1000
    private val mutationRate = 0.01
    private var route: List<Location> = emptyList()
    private var matrix: Map<String, Map<String, Double>> = emptyMap()

    override fun solve(route: List<Location>, matrix: Map<String, Map<String, Double>>): TSPBestSolution {
        TODO("Not yet implemented")
    }

    // 초기 개체군 생성
    private fun initializePopulation(popSize: Int): List<Individual> {
        return List(popSize) {
            val path = route.shuffled().toMutableList()
            val fitness = evaluateFitness(path)
            Individual(path, fitness)
        }
    }

    // 경로의 적합도(총 비용) 계산
    private fun evaluateFitness(path: List<Location>): Double {
        var totalCost = 0.0
        for (i in 0 until path.size - 1) {
            totalCost += matrix[path[i].id]!![path[i + 1].id]!!
        }
        return totalCost
    }

    data class Individual(val path: List<Location>, var fitness: Double = Double.MAX_VALUE)
}