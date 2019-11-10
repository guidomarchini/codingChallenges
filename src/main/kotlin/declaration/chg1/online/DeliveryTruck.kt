package declaration.chg1.online

interface DeliveryTruck {
    /**
     * Given the truck's maximum distance and the nodes distances,
     * returns the travel that visits the most nodes.
     */
    fun bestTravel(maximumDistance: Int,
                   nodesDistances: List<Int>
                   ): List<Int>
}