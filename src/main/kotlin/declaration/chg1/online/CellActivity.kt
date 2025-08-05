package declaration.chg1.online

class CellActivity {
    /**
     * Each cell has a state: ON (1) or OFF (0).
     * After each second, a cell changes it state by the next predicate:
     * If the cells around it are both ON or OFF,
     * then the cell state becomes ON. Otherwise, it turns OFF.
     *
     * Given the actual cell states and the seconds we need to predict,
     * return the states that the cells would have after those seconds.
     */
    fun predictCellActivity(
        seconds: Int,
        cellStates: List<Int>
    ): List<Int> {
        return generateSequence(cellStates) { currentStates ->
            currentStates.mapIndexed{ index, _ ->
                val left = if (index == 0) currentStates.last() else currentStates[index - 1]
                val right = if (index == currentStates.size - 1) currentStates.first() else currentStates[index + 1]

                if (left == right) 1 // Both ON or OFF = ON
                else 0
            }
        }.elementAt(seconds + 1)
    }
}