package declaration.chg5

interface TreeChopper {
    /**
     * You have a garden with trees, every tree is aligned.
     * You want to ensure that for every tree, each adjacent tree
     * either are taller or smaller. If this doesn't apply,
     * then you have to chop down a tree in order to ensure the above.
     * Given a list represents the height of the trees,
     * return the quantity of trees that you need to chop down to ensure the condition.
     */
    fun treesToChopDown(treeHeights: List<Int>): Int
}