package preparation.firstCodingPrep

/**
 * Problem: Given a List<String?> (a list that might contain null strings), process it as follows:
 *
 * Filter out any null elements.
 * Filter out any empty or blank strings (e.g., "", " ").
 * For the remaining valid strings, transform them to UPPERCASE.
 * Join all the processed strings into a single string, separated by a comma and a space (", ").
 * Example:
 * Input: listOf("hello", null, " ", "world", "kotlin", "")
 * Expected Output: "HELLO, WORLD, KOTLIN"
 */
fun joinProcessedStrings(stringList: List<String?>): String {
    return stringList
        .filter { string -> string.let { notNull -> notNull?.isNotBlank() } ?: false }
//        .filterNotNull()
//        .filter { it.isNotBlank() }
        .joinToString(", ")
        .toUpperCase()
}

/**
 * Problem: Define a data class called Transaction with properties id: String, amount: Double, and currency: String.
 * Given a List<Transaction>, calculate the total amount for each unique currency.
 *
 * Example:
 * Input:
 * val transactions = listOf(
 *     Transaction("t1", 100.0, "USD"),
 *     Transaction("t2", 50.0, "EUR"),
 *     Transaction("t3", 75.0, "USD"),
 *     Transaction("t4", 200.0, "EUR"),
 *     Transaction("t5", 25.0, "GBP")
 * )
 * Expected Output:
 * {"USD": 175.0, "EUR": 250.0, "GBP": 25.0} (Order of map entries doesn't matter)
 */

data class Transaction(
    val id: String,
    val amount: Double,
    val currency: String
)

fun calculateTotalAmountByCurrency(transactions: List<Transaction>): Map<String, Double> {
    return transactions.groupBy { it.currency }
        .mapValues {
            it.value.sumByDouble { transaction -> transaction.amount }
        }
}


/**
 * Conditional Logic & Basic Iteration
 * Problem: Write a function customFizzBuzz(n: Int) that prints numbers from 1 to n.
 * But for multiples of three print "Fizz", for multiples of five print "Buzz". For numbers that are multiples of both three and five print "FizzBuzz".
 * Additionally, if the number contains the digit '7', print "Lucky!" instead, overriding any Fizz/Buzz rule.
 *
 * Example (n=15 output excerpt):
 * ...
 * 6
 * Lucky! (for 7)
 * 8
 * Fizz (for 9)
 * 10
 * 11
 * Fizz (for 12)
 * Lucky! (for 13, because it contains 7, overrides Fizz)
 * 14
 * FizzBuzz (for 15)
 */
fun customFizzBuzz(n: Int) {
    for (i in 1..n) {
        when {
            i.toString().contains('7') -> println("Lucky!")
            i % 3 == 0 && i % 5 == 0 -> println("FizzBuzz")
            i % 3 == 0 -> println("Fizz")
            i % 5 == 0 -> println("Buzz")
            else -> println(i)
        }
    }
}


//fun main() {
//    sampleJoinProcessedStrings()
//}
