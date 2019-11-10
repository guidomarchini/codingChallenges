package declaration.chg1.onsite

interface JumpingBoard {
    /**
     * You are given a list of Integers. Each Integer defines the maximum jumps that can be made.
     * The list could have a 0, which means that the game would be over.
     * You have to define whether the board can be finished or not.
     */
    fun canBeFinished(board: List<Int>): Boolean
}