
import java.util.*;

class Solution {

    class State {
        int node;
        int count;
        long distance;

        State(int node, int count, long distance) {
            this.node = node;
            this.count = count;
            this.distance = distance;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Build adjacency list
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj[u].add(new int[]{v, w});
        }

        // dist[node][count]
        long INF = Long.MAX_VALUE / 4;
        long[][] dist = new long[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        // Starting state
        dist[0][1] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a.distance, b.distance)
        );

        pq.offer(new State(0, 1, 0));

        while (!pq.isEmpty()) {

            State curr = pq.poll();

            int u = curr.node;
            int count = curr.count;
            long d = curr.distance;

            // Ignore outdated states
            if (d != dist[u][count]) {
                continue;
            }

            // Explore outgoing edges
            for (int[] edge : adj[u]) {

                int v = edge[0];
                int weight = edge[1];

                int newCount;

                if (labels.charAt(u) == labels.charAt(v)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                // Invalid consecutive sequence
                if (newCount > k) {
                    continue;
                }

                long newDist = d + weight;

                if (newDist < dist[v][newCount]) {

                    dist[v][newCount] = newDist;

                    pq.offer(
                        new State(v, newCount, newDist)
                    );
                }
            }
        }

        // Find minimum distance among all valid states
        long ans = INF;

        for (int count = 1; count <= k; count++) {
            ans = Math.min(ans, dist[n - 1][count]);
        }

        return ans == INF ? -1 : (int) ans;
    }
}
