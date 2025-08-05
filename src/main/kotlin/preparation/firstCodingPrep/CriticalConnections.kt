package preparation.firstCodingPrep

/**
 * Exercise 3: Critical Connections in a Network (Graph/Tarjan's/Bridge-Finding)
 * Problem: There are n servers numbered from 0 to n - 1 connected by undirected connections forming a network
 * where connections[i] = [a, b] represents a connection between servers a and b.
 * A connection is critical if removing it will make some server or group of servers unable to reach some other server or group of servers.
 * Return all critical connections in the network. You may return the answer in any order.
 *
 * Example:
 * n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: Removing the connection [1,3] disconnects server 3 from the rest of the network.
 *
 * Senior Level Considerations:
 *
 * Graph Theory: This is a classic bridge-finding problem. Understanding concepts like bridges, articulation points, and cycles.
 * Algorithms: Tarjan's algorithm or a similar algorithm based on DFS is typically used. This involves tracking discovery times and low-link values.
 * Implementation Complexity: Implementing Tarjan's correctly requires careful management of visited states, parent pointers, discovery times, and back-edges.
 * Correctness Proof/Intuition: Being able to explain why your algorithm works in terms of graph properties.
 * Edge Cases: Disconnected components, graphs with only one edge, complete graphs.
 */
class CriticalConnections {

    private lateinit var adj: MutableMap<Int, MutableList<Int>> // Adjacency list
    private lateinit var disc: IntArray // Discovery time
    private lateinit var low: IntArray // Low-link value
    private lateinit var visited: BooleanArray
    private var time: Int = 0 // Global timer for discovery times
    private val bridges: MutableList<List<Int>> = mutableListOf()

    fun findCriticalConnections(n: Int, connections: List<List<Int>>): List<List<Int>> {
        adj = mutableMapOf()
        for (i in 0 until n) {
            adj[i] = mutableListOf()
        }
        for (conn in connections) {
            val u = conn[0]
            val v = conn[1]
            adj[u]?.add(v)
            adj[v]?.add(u) // Undirected graph
        }

        disc = IntArray(n) { -1 } // Initialize with -1 to indicate unvisited
        low = IntArray(n) { -1 }
        visited = BooleanArray(n) { false }
        time = 0
        bridges.clear()

        // Iterate through all nodes to handle disconnected components
        for (i in 0 until n) {
            if (!visited[i]) {
                dfs(i, -1) // -1 indicates no parent for the root of the DFS tree
            }
        }
        return bridges
    }

    private fun dfs(u: Int, parent: Int) {
        visited[u] = true
        disc[u] = time
        low[u] = time
        time++ // Increment global time for next node

        adj[u]?.forEach { v ->
            if (v == parent) {
                return@forEach // Skip the immediate parent (don't go back up the tree)
            }

            if (visited[v]) {
                // Back-edge detected (v is already visited and not the parent)
                // This means v is an ancestor of u, and we found a cycle.
                // Update low[u] because u can reach v (and thus v's ancestors).
                low[u] = minOf(low[u], disc[v])
            } else {
                // Tree edge: v is unvisited
                dfs(v, u) // Recurse on v, setting u as its parent

                // After recursive call returns:
                // Update low[u] based on low[v].
                // This means u can reach whatever v's subtree can reach.
                low[u] = minOf(low[u], low[v])

                // Bridge Condition:
                // If low[v] > disc[u], it means v and its subtree
                // can't reach any node with a discovery time earlier than disc[u]
                // (i.e., v's subtree cannot reach u or u's ancestors without using edge (u,v)).
                // Therefore, (u,v) is a bridge.
                if (low[v] > disc[u]) {
                    bridges.add(listOf(u, v))
                }
            }
        }
    }
}