package declaration.chg1.online

interface BusMusic {
    /**
     * given the bus travel duration and the song durations,
     * returns the best pair of songs that can be reproduced,
     * leaving 30 seconds in silence.
     */
    fun bestTwoDurations(travelDuration: Int,
                         songDurations: List<Int>
                         ): Pair<Int, Int>
}