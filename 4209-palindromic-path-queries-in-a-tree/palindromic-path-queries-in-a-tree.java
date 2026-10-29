
import java.util.*;

class Solution {

    private int[][] up;
    private int[] depth, tin, tout, parent;
    private int[] prefixMask, subtreeSize;
    private int timer, log;
    private Fenwick fenwick;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        log = 1;
        while ((1 << log) <= n) {
            log++;
        }

        up = new int[log][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        parent = new int[n];
        prefixMask = new int[n];
        subtreeSize = new int[n];

        char[] chars = s.toCharArray();

        buildTree(n, graph, chars);

        fenwick = new Fenwick(n);

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldBit = 1 << (chars[u] - 'a');
                int newBit = 1 << (c - 'a');
                int delta = oldBit ^ newBit;

                if (delta != 0) {
                    fenwick.rangeXor(tin[u], tout[u], delta);
                    chars[u] = c;
                }

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v);

                int maskU =
                    prefixMask[u] ^ fenwick.query(tin[u]);

                int maskV =
                    prefixMask[v] ^ fenwick.query(tin[v]);

                int lcaBit = 1 << (chars[lca] - 'a');

                int pathMask = maskU ^ maskV ^ lcaBit;

                answer.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        return answer;
    }

    private void buildTree(
            int n, List<Integer>[] graph, char[] chars) {

        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;
        depth[0] = 0;
        prefixMask[0] = 1 << (chars[0] - 'a');

        for (int k = 0; k < log; k++) {
            up[k][0] = 0;
        }

        timer = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = timer;
            order[timer++] = u;

            for (int v : graph[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;

                prefixMask[v] =
                    prefixMask[u] ^ (1 << (chars[v] - 'a'));

                up[0][v] = u;

                for (int k = 1; k < log; k++) {
                    up[k][v] = up[k - 1][up[k - 1][v]];
                }

                stack[top++] = v;
            }
        }

        Arrays.fill(subtreeSize, 1);

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];

            if (u != 0) {
                subtreeSize[parent[u]] += subtreeSize[u];
            }
        }

        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + subtreeSize[u] - 1;
        }
    }

    private int getLCA(int u, int v) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < log; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = log - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    private static class Fenwick {
        private final int[] bit;
        private final int n;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 1];
        }

        private void update(int index, int value) {
            for (int i = index; i <= n; i += i & -i) {
                bit[i] ^= value;
            }
        }

        void rangeXor(int left, int right, int value) {
            update(left + 1, value);
            update(right + 2, value);
        }

        int query(int index) {
            int result = 0;

            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= bit[i];
            }

            return result;
        }
    }
}
