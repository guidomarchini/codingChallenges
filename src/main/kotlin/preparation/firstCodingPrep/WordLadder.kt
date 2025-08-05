package preparation.firstCodingPrep

import java.util.ArrayDeque

/**
 * Exercise 1: Word Ladder II (Graph/BFS/Backtracking)
 * Problem: Given two words, beginWord and endWord, and a dictionary wordList, return all shortest transformation sequences from beginWord to endWord. If no such sequence exists, return an empty list.
 *
 * A transformation sequence from word1 to word2 is a sequence word1 -> wordA -> wordB -> ... -> word2 such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every word in the sequence (except beginWord) is in wordList.
 * beginWord does not need to be in wordList.
 * endWord must be in wordList.
 * Example:
 * beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output:
 *
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 *
 * Senior Level Considerations:
 * Graph Representation: How do you efficiently build the graph of words? (Adjacency list is common)
 * Shortest Path: BFS is key here to find the shortest path.
 * Finding ALL Shortest Paths: This is the tricky part. A standard BFS only finds one shortest path. You need to adapt BFS to explore all paths of minimal length. You might need to build a "parent" map or reconstruct paths using a second traversal (e.g., DFS after BFS).
 * Optimization: How to efficiently check for single-letter differences between words? Pre-processing or clever iteration.
 * Large wordList: Consider performance implications.
 *
 */
class WordLadder {

    fun main(args: Array<String>) {
        // Example usage
        val beginWord = "hit"
        val endWord = "cog"
        val wordList = listOf("hot", "dot", "dog", "lot", "log", "cog")

//        val result = findLadders(beginWord, endWord, wordList)
//        println(result)
    }

    private fun findLadders(beginWord: String, endWord: String, wordList: List<String>): List<String> {
        val wordSet = wordList.toHashSet()
        // Optional: Add beginWord to wordSet if you want to consider it for intermediate steps,
        // though the problem statement says "except beginWord", so maybe not always needed
        // based on exact interpretation for intermediate paths. For finding neighbors, it's fine.
        // If endWord is not in wordSet, return empty list immediately.
        if (!wordSet.contains(endWord)) {
            return emptyList()
        }

        val wordsQueue = ArrayDeque<String>()
        wordsQueue.addFirst(beginWord)

        return emptyList()
    }

    fun findNeighbors(word: String, wordSet: Set<String>): List<String> {
        val neighbors = mutableListOf<String>()
        val charArray = word.toCharArray() // Convert to char array for easy modification

        for (i in charArray.indices) { // Iterate through each character position
            val originalChar = charArray[i] // Store original character
            for (c in 'a'..'z') { // Try replacing with every letter from 'a' to 'z'
                if (c == originalChar) continue // Skip if it's the same letter

                charArray[i] = c // Replace the character
                val newWord = String(charArray) // Convert back to string

                if (wordSet.contains(newWord)) { // Check if the new word is in our dictionary
                    neighbors.add(newWord)
                }
            }
            charArray[i] = originalChar // Backtrack: restore the original character for the next iteration
        }
        return neighbors
    }


}