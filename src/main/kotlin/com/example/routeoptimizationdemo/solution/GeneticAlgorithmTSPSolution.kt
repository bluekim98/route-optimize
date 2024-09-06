package com.example.routeoptimizationdemo.solution

import com.example.routeoptimizationdemo.data.Location
import com.example.routeoptimizationdemo.data.TSPBestSolution
import kotlin.math.min
import kotlin.random.Random

class GeneticAlgorithmTSPSolution : TSPSolution {
    private var N = 0
    private val populationSize = 100
    private val generations = 1000
    private val mutationRate = 0.01
    private var route: List<Location> = emptyList()
    private var matrix: Map<String, Map<String, Double>> = emptyMap()

    override fun solve(route: List<Location>, matrix: Map<String, Map<String, Double>>): TSPBestSolution {
        this.N = route.size
        this.route = route
        this.matrix = matrix
        var population = initializePopulation(populationSize)

        for (generation in 1..generations) {
            population = evolvePopulation(population, mutationRate)
            val bestIndividual = population.minByOrNull { it.fitness }!!
            println("Generation $generation: Best Fitness = ${bestIndividual.fitness}, Path = ${bestIndividual.path}")
        }

        val bestOverall = population.minByOrNull { it.fitness }!!

        return TSPBestSolution(bestOverall.path, bestOverall.fitness)
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

    // 부모 선택 (토너먼트 선택)
    fun selectParent(population: List<Individual>): Individual {
        val tournament = List(3) { population.random() }
        return tournament.minByOrNull { it.fitness }!!
    }

    // 교차 연산 (PMX 교차)
    fun crossover(parent1: Individual, parent2: Individual): Individual {
        val crossoverPoint1 = Random.nextInt(N)
        val crossoverPoint2 = Random.nextInt(N)
        val start = min(crossoverPoint1, crossoverPoint2)
        val end = maxOf(crossoverPoint1, crossoverPoint2)

        val childPath = MutableList(N) { Location.empty() }

        for (i in start..end) {
            childPath[i] = parent1.path[i]
        }

        var current = end + 1
        for (i in 0 until N) {
            val index = (end + 1 + i) % N
            if (parent2.path[index] !in childPath) {
                childPath[current % N] = parent2.path[index]
                current++
            }
        }

        val child = Individual(childPath)
        child.fitness = evaluateFitness(child.path)
        return child
    }

    // 변이 연산 (스왑 변이)
    fun mutate(individual: Individual) {
        val index1 = Random.nextInt(N)
        val index2 = Random.nextInt(N)
        individual.path[index1] = individual.path[index2].also { individual.path[index2] = individual.path[index1] }
        individual.fitness = evaluateFitness(individual.path)
    }

    // 다음 세대 생성
    fun evolvePopulation(population: List<Individual>, mutationRate: Double): List<Individual> {
        val newPopulation = mutableListOf<Individual>()
        val elite = population.minByOrNull { it.fitness }!!
        newPopulation.add(elite) // 엘리트 선택(최적해 유지)

        while (newPopulation.size < population.size) {
            val parent1 = selectParent(population)
            val parent2 = selectParent(population)
            val child = crossover(parent1, parent2)

            if (Random.nextDouble() < mutationRate) {
                mutate(child)
            }

            newPopulation.add(child)
        }

        return newPopulation
    }

    data class Individual(val path: MutableList<Location>, var fitness: Double = Double.MAX_VALUE)
}