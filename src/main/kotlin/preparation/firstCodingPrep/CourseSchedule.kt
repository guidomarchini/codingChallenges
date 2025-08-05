package preparation.firstCodingPrep

/**
 * Exercise 2: Course Schedule III (Graph/Topological Sort/Priority Queue)
 * Problem: There are n courses that have to be taken sequentially.
 * You are given n courses represented by [duration_i, lastDay_i] where duration_i is the length of the course and lastDay_i is the last day that the course can be taken.
 * The i-th course should be taken before or on lastDay_i. You start on day 0 and you can only take one course at a time. Return the maximum number of courses that can be taken.
 *
 * Example:
 * courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
 * Output: 3
 * Explanation:
 *
 * Take course 0 (duration=100,lastDay=200). Current time = 100.
 * Take course 1 (duration=200,lastDay=1300). Current time = 100 + 200 = 300.
 * Take course 2 (duration=1000,lastDay=1250). Current time = 300 + 1000 = 1300. (Course 2 finishes on day 1300, which is <= lastDay_2).
 * Course 3 cannot be taken because it finishes on day 1300+2000=3300>3200. The total number of courses taken is 3.
 * Senior Level Considerations:
 *
 * Greedy Approach: How to sort the courses? Sorting by lastDay seems intuitive, but is it enough?
 * Dynamic Decision Making: If you take a course, and later realize it was a "bad" choice (e.g., it prevents you from taking more courses), how do you "undo" or optimize?
 * Data Structure for Optimization: A PriorityQueue (Min-Heap or Max-Heap)
 * is often involved in greedy problems to keep track of the "best" or "worst" choice made so far for potential replacement.
 * Time Management: Keeping track of the current day.
 *
 */
import java.util.PriorityQueue
import java.util.Collections // For reverseOrder() to create a Max-Heap

data class Course(val duration: Int, val lastDay: Int)

fun scheduleCourse(courses: Array<IntArray>): Int {
    // 1. Convert to data class and sort by lastDay (ascending)
    val sortedCourses = courses.map { Course(it[0], it[1]) }
        .sortedBy { it.lastDay } // Sorted by 'lastDay'

    // 2. Max-Heap to store durations of courses currently taken
    // Using Collections.reverseOrder() makes it a Max-Heap (largest element at the top)
    val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())

    var currentTime = 0 // Tracks total time spent on courses in the heap

    for (course in sortedCourses) {
        // Scenario 1: Can take the course directly
        if (currentTime + course.duration <= course.lastDay) {
            currentTime += course.duration
            maxHeap.offer(course.duration) // Add duration to heap
        } else {
            // Scenario 2: Cannot take directly, try to swap
            // Check if heap is not empty AND current course is shorter than the longest one already taken
            if (maxHeap.isNotEmpty() && course.duration < maxHeap.peek()!!) {
                currentTime -= maxHeap.poll()!! // Remove longest duration
                currentTime += course.duration    // Add current course duration
                maxHeap.offer(course.duration)    // Add new course duration to heap
            }
            // Else: Cannot take this course, just skip it.
        }
    }

    // The number of courses taken is the number of elements in the heap
    return maxHeap.size
}

//fun main() {
// Example Usage:
//    val courses1 = arrayOf(intArrayOf(100,200), intArrayOf(200,1300), intArrayOf(1000,1250), intArrayOf(2000,3200))
//    println(scheduleCourse(courses1)) // Output: 3
//
//    val courses2 = arrayOf(intArrayOf(1,2))
//    println(scheduleCourse(courses2)) // Output: 1
//
//    val courses3 = arrayOf(intArrayOf(3,2), intArrayOf(4,3))
//    println(scheduleCourse(courses3)) // Output: 0

//    val courses4 = arrayOf(intArrayOf(100, 118), intArrayOf(9, 119), intArrayOf(10, 120))
//    println(scheduleCourse(courses4)) // Output: 2
//}