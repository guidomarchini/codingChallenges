package declaration.chg5

interface SnakeBoard {
    /**
     * You are given a board with Integers.
     * Each cell is connected with each adjacent one (East, West, North, South).
     * You can connect 4 cells and represent a number of 4 digits.
     * The task is to find the highest number that you can extract from the board.
     */
    fun highestNumber(board: List<List<Int>>): Int
}