class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        // Sort logs by timestamp
        Arrays.sort(logs, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        int k = queries.length;

        // Store query time and original index
        int[][] sortedQueries = new int[k][2];

        for (int i = 0; i < k; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort queries by time
        Arrays.sort(sortedQueries, (a, b) ->
            Integer.compare(a[0], b[0])
        );

        int[] ans = new int[k];

        // Frequency of requests for each server
        int[] freq = new int[n + 1];

        int left = 0;
        int right = 0;

        int activeServers = 0;

        for (int i = 0; i < k; i++) {

            int q = sortedQueries[i][0];
            int low = q - x;

            // Add logs with timestamp <= q
            while (right < logs.length &&
                   logs[right][1] <= q) {

                int server = logs[right][0];

                if (freq[server] == 0) {
                    activeServers++;
                }

                freq[server]++;
                right++;
            }

            // Remove logs with timestamp < low
            while (left < right &&
                   logs[left][1] < low) {

                int server = logs[left][0];

                freq[server]--;

                if (freq[server] == 0) {
                    activeServers--;
                }

                left++;
            }

            // Store answer at original query index
            ans[sortedQueries[i][1]] = n - activeServers;
        }

        return ans;
    }
}