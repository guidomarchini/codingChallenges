package preparation.secondCodingPrep

import java.util.Stack

/**
 * Ejercicio 1 – LeetCode Style – Nivel Medio (Kotlin)
 *
 * Problem: Most Frequent K Elements
 * Dado un array de chars y un número entero k, devolver los k elementos más frecuentes.
 *
 * Ejemplo:
 *
 * kotlin
 * val chars = listOf('a', 'b', 'c', 'a', 'b', 'a')
 * val k = 2
 * topKFrequent(chars, k) // ['a', 'b']
 */
//fun main(args: Array<String>) {
//    val x: Any = "Hello"
//    if (x is String) { println(x.length) }
//
//    MostFrequentKElement.mostFrequentWithPriorityQueue(
//        listOf('a', 'b', 'c', 'a', 'b', 'a'),
//        2
//    ).also { println("Most frequent characters: $it") }
//}


object MostFrequentKElement {


    fun bruteForce(
        chars: List<Char>,
        k: Int
    ): List<Char> {
        return chars.groupBy { it }
            .mapValues { it.value.size }
            .asSequence()
            .sortedBy { it.value }
            .map { it.key }
            .take(k)
            .toList()
    }

    fun bruteForceGroupingBy(
        chars: List<Char>,
        k: Int
    ): List<Char> {
        return chars.groupingBy { it }
            .eachCount()
            .entries
            .sortedByDescending { it.value }
            .take(k)
            .map { it.key }
    }

    fun mostFrequentWithPriorityQueue(
        chars: List<Char>,
        k: Int
    ): List<Char> {
        val frequencyMap = chars.groupingBy { it }.eachCount()
        val priorityQueue = java.util.PriorityQueue<Map.Entry<Char, Int>>(
            compareBy { it.value }
        )

        for (entry in frequencyMap) {
            priorityQueue.add(entry)

            if (priorityQueue.size > k) {
                priorityQueue.poll()
            }
        }

        return priorityQueue
            .sortedByDescending { it.value } // si importa el orden de frecuencia
            .map { it.key }
    }
}