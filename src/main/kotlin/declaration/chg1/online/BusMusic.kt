package declaration.chg1.online

class BusMusic {
    /**
     * given the bus travel duration and the song durations,
     * returns the best pair of songs that can be reproduced,
     * leaving 30 seconds in silence.
     */
    fun bestTwoDurations(
        travelDuration: Int,
        songDurations: List<Int>
    ): Pair<Int, Int> {
        if (songDurations.size < 2) {
            throw RuntimeException("Songs should be at least 2")
        }
        val maxDuration = travelDuration - 30

        return innerCheck(songDurations, maxDuration)
    }

    private tailrec fun innerCheck(
        songDurations: List<Int>,
        maxDuration: Int
    ): Pair<Int, Int> {

        if (songDurations.size < 2) {
            throw RuntimeException("No pair found")
        }

        val head : Int = songDurations.first()
        return songDurations.drop(1)
            .find { it + head == maxDuration }
            ?.let { Pair(head, it) }
            ?: innerCheck(
                songDurations.drop(1),
                maxDuration
            )
    }
}