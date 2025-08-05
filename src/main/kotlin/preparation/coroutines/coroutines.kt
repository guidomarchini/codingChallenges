package preparation.coroutines

import kotlinx.coroutines.*
import kotlin.random.Random

//fun main() = runBlocking {
//    println("🏁 Carrera iniciada...\n")
//    race(runners = listOf("Sonic", "Flash", "RoadRunner", "Usain"))
//    println("\n🎉 ¡Todos llegaron!")
//}

suspend fun race(runners: List<String>) {
    val results = mutableListOf<Pair<String, Long>>()

    coroutineScope {
        runners.map { runner ->
            async {
                Random.nextLong(500, 1500)
                    .also { delay(it)  }
                val time = Random.nextLong(500, 1500)
                delay(time)
                results.add(runner to time)
            }
        }.awaitAll()
    }

    println("\n📊 Resultados:")
    results.sortedBy { it.second }.forEachIndexed { index, (runner, time) ->
        println("${index + 1}. $runner - ${time}ms")
    }
}
