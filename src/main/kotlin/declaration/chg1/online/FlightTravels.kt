package declaration.chg1.online

interface FlightTravels {
    /**
     * Given the maximum distance a flight can travel,
     * and a list of the distances between the nodes,
     * return the pair of indexes for the longest travel.
     */
    fun bestRoutes(maximumDistance: Int,
                   travelDistances: List<Int>
                   ): Pair<Int, Int>
}