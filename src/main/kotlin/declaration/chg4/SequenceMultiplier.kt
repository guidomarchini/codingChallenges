package declaration.chg4

interface SequenceMultiplier {
    /**
     * Given a sequence of Integers, returns a sequence of the same size,
     * where each index is the product of every number, without the element of that index.
     * Ex:
     * Input: Seq[1, 2, 3, 4]
     * Output: Seq[2*3*4, 1*3*4, 1*2*4, 1*2*3]
     */
    fun multiplyEachOther(elements: List<Int>): List<Int>
}