package declaration.chg1.onsite

interface MedianCalculator {
    /** Adds an element */
    fun add(element: Int): Unit

    /** Calculates the median */
    fun calculateMedian(): Int
}