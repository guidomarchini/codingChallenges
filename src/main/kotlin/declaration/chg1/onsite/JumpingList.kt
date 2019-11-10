package declaration.chg1.onsite

interface JumpingList {
    /**
     * You have a list of Ints, which represents the maximum jumps that can be made.
     * Example: 3 can jump 1, 2 or 3 cells.
     * Given a board, return the minimum jumps needed to reach the end of the list.
     */
    fun minimumJumps(jumps: List<Int>): Int
}