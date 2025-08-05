package preparation.firstCodingPrep

data class Interval(val start: Int, val end: Int)


fun mergeIntervalsWithLimit(intervals: List<Interval>, maxDuration: Int): List<Interval> {
    if (intervals.isEmpty()) return emptyList()

    val sortedIntervals = intervals.sortedBy { it.start }
    val mergedIntervals = mutableSetOf<Interval>()
    val intervalsInReview = mutableListOf<Interval>()

    for (newInterval in sortedIntervals) {
        val intervalsToRemove = mutableListOf<Interval>()
        val intervalsToAdd = mutableListOf<Interval>(newInterval)

        for (reviewed in intervalsInReview) {
            if (!isAdjacent(reviewed, newInterval)) {
                mergedIntervals.add(reviewed)
                intervalsToRemove.add(reviewed)
            } else if (canExpand(reviewed, newInterval, maxDuration)) {
                intervalsToRemove.add(reviewed)
                intervalsToAdd.add(Interval(reviewed.start, newInterval.end))
            }
        }

        intervalsInReview.removeAll(intervalsToRemove)
        intervalsInReview.addAll(intervalsToAdd)
    }
    mergedIntervals.addAll(intervalsInReview)

    return mergedIntervals.toList()
}

fun isAdjacent(
    reviewed: Interval,
    newInterval: Interval
): Boolean {
    return reviewed.end > newInterval.start;
}

fun canExpand(
    reviewed: Interval,
    newInterval: Interval,
    maxDuration: Int
): Boolean {
    return newInterval.end > reviewed.end && (newInterval.end - reviewed.start) <= maxDuration
}


//fun main() {
//    val intervals = listOf(
//        Interval(1, 3),
//        Interval(2, 5),
//        Interval(6, 8),
//        Interval(7, 10)
//    )
//    val maxDuration = 5
//
//    val merged = mergeIntervalsWithLimit(intervals, maxDuration)
//    println("Merged Intervals: $merged")
//}