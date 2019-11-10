package declaration.chg1.online

interface FirstDuplicate {
    /** adds a number */
    fun add(element: Int): Unit

    /** returns the first duplicate, removing it from the list */
    fun firstDuplicate(): Int
}