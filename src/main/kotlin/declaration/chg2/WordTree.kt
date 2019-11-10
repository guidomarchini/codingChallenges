package declaration.chg2

interface WordTree {
    /**
     * Returns the word represented by this tree.
     * The tree is inorder traversal.
     */
    override fun toString(): String

    fun equals(anotherTree: WordTree): Boolean
}