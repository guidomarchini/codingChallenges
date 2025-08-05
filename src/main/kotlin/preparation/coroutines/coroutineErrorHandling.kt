package preparation.coroutines

import kotlinx.coroutines.*

//fun main(): Unit = runBlocking {
//    fetchWithSupervisorScope()
//}

suspend fun fetchWithCoroutineScope() = coroutineScope {
    val user = async {
        delay(100)
        println("✅ User fetched")
        "User"
    }

    val orders = async {
        delay(200)
        println("💥 Orders failed")
        throw RuntimeException("Order service down")
    }

    val recs = async {
        delay(300)
        println("📦 Recommendations fetched")
        "Recs"
    }

    // El await del fallo cancela TODO el scope.
    try {
        listOf(user.await(), orders.await(), recs.await())
    } catch (e: Exception) {
        println("❌ Exception: ${e.message}")
        emptyList()
    }
}

suspend fun fetchWithSupervisorScope() = supervisorScope {
    val user = async {
        delay(100)
        println("✅ User fetched")
        "User"
    }

    val orders = async {
        delay(200)
        println("💥 Orders failed")
        throw RuntimeException("Order service down")
    }

    val recs = async {
        delay(300)
        println("📦 Recommendations fetched")
        "Recs"
    }

    val safeUser = try { user.await() } catch (e: Exception) { "DefaultUser" }
    val safeOrders = try { orders.await() } catch (e: Exception) {
        println("⚠️ Orders failed but continuing: ${e.message}")
        "NoOrders"
    }
    val safeRecs = try { recs.await() } catch (e: Exception) { "NoRecs" }

    println("🎉 Result: $safeUser, $safeOrders, $safeRecs")
}
