package declaration.chg1.online

interface CellActivity {
    /**
     * Each cell has a state: ON (1) or OFF (0).
     * After each second, a cell changes it state by the next predicate:
     * If the cells around it are both ON or OFF,
     * then the cell state becomes ON. Otherwise, it turns OFF.
     *
     * Given the actual cell states and the seconds we need to predict,
     * return the states that the cells would have after those seconds.
     */
    fun predictCellActivity(seconds: Int,
                            cellStates: List<Int>
                            ): List<Int>
}