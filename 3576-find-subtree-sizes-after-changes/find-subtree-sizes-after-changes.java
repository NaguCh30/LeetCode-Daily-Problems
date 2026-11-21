class Solution {

    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        List<Integer>[] children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        // Build the original tree
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] newParent = parent.clone();

        // Stores the closest ancestor for each character
        int[] last = new int[26];
        Arrays.fill(last, -1);

        // Determine new parents using the original tree
        dfs(0, children, newParent, last, s);

        // Build the modified tree
        List<Integer>[] newChildren = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        int[] answer = new int[n];

        calculateSizes(0, newChildren, answer);

        return answer;
    }

    private void dfs(int node, List<Integer>[] children,
                     int[] newParent, int[] last, String s) {

        int c = s.charAt(node) - 'a';

        int previous = last[c];

        if (previous != -1) {
            newParent[node] = previous;
        }

        last[c] = node;

        for (int child : children[node]) {
            dfs(child, children, newParent, last, s);
        }

        // Backtrack to restore the ancestor state
        last[c] = previous;
    }

    private int calculateSizes(int node, List<Integer>[] children,
                               int[] answer) {

        int size = 1;

        for (int child : children[node]) {
            size += calculateSizes(child, children, answer);
        }

        answer[node] = size;

        return size;
    }
}
