package declaration.chg1.onsite

interface EulerianWords {
    /**
     * A string is said to be connected with another one if
     * one last character is the same as the first character of the other.
     * Given a list of strings, check the following
     * * Every String is connected.
     * * We can sort the connections in order that the drawing can form a circle.
     * For example, [AB, BC, CA] would return true, as
     *      AB
     *     /  \
     *   BC - CA
     *
     * Another one: [AB, BC, CD] would return false, as we can't draw a circle
     *  AB -> BC -> CD
     */
    fun isEulerian(words: List<String>): Boolean
}