package declaration.chg3

interface SumChecker {
    /**
     * Adds an element
     */
    fun add(element: Int): Unit

    /**
     * Checks if exists TWO elements that sums the given number
     */
    fun sumCheck(wantedTotal: Int): Boolean
}