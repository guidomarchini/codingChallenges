package preparation.firstCodingPrep

data class Item(val name: String, val popularity: Int)

fun recommendProducts(
    userId: String,
    userPurchases: Map<String, Set<Item>>, // Mapa userId -> conjunto de productoIds
    k: Int
): List<Item> {
    return userPurchases.get(userId)?.let { purchasedItems ->
        userPurchases
            .filterKeys { it != userId }
            .map { (_, items) -> items }
            .filter { purchasedItems.intersect(it).size >= k }
            .flatMap { it.minus(purchasedItems) }
            .distinct()
            .sortedBy { it.popularity }
    } ?: emptyList()
}

fun recommendProductsAsSequence(
    userId: String,
    userPurchases: Map<String, Set<Item>>, // Mapa userId -> conjunto de productoIds
    k: Int
): List<Item> {

    val userItems: Set<Item> = userPurchases[userId]!!
    return userPurchases.asSequence()
        .filter{ (key, items) -> key != userId && hasAtLeastKCommonItems(userItems, items, k) }
        .flatMap { (_, items) -> items.minus(userItems).asSequence() }
        .distinct()
        .sortedByDescending { it.popularity }
        .toList()
}

fun recommendProductsWithRelativePopularity(
    userId: String,
    userPurchases: Map<String, Set<Item>>, // Mapa userId -> conjunto de productoIds
    k: Int
): List<Item> {
    val purchasedItems: Set<Item> = userPurchases[userId]!!
    return userPurchases.asSequence()
        .filter { (key, items) -> key != userId && hasAtLeastKCommonItems(purchasedItems, items, k) }
        .flatMap { it.value.asSequence().filter { item -> item !in purchasedItems } }
        .groupingBy { it }
        .eachCount()
        .entries
        .sortedByDescending { it.value }
        .map { it.key }
        .toList()
}

fun hasAtLeastKCommonItems(
    userPurchases: Set<Item>,
    otherUserPurchases: Set<Item>,
    k: Int
): Boolean {
    var count = 0
    for (item in userPurchases) {
        if (otherUserPurchases.contains(item)) {
            count++
            if (count >= k) return true
        }
    }
    return false
}