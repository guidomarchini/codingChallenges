package declaration.chg3

interface ListInterception {
    /**
     * Given two lists, returns the elements that both share.
     */
    fun interception(list1: List<Int>,
                     list2: List<Int>
                     ): List<Int>
}