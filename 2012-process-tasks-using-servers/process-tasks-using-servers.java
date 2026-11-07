import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return a[0] - b[0];

                return a[1] - b[1];
            }
        );

        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Long.compare(a[0], b[0]);

                if (a[1] != b[1])
                    return Long.compare(a[1], b[1]);

                return Long.compare(a[2], b[2]);
            }
        );

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // Add all servers to the available queue.
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        long time = 0;

        for (int j = 0; j < m; j++) {

            time = Math.max(time, j);

            // Move completed servers to available queue.
            while (!busy.isEmpty() && busy.peek()[0] <= time) {

                long[] server = busy.poll();

                available.offer(new int[]{
                    (int) server[1], (int) server[2]
                });
            }

            // If no server is available, wait.
            if (available.isEmpty()) {

                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {

                    long[] server = busy.poll();

                    available.offer(new int[]{
                        (int) server[1], (int) server[2]
                    });
                }
            }

            // Select the server with minimum weight and index.
            int[] server = available.poll();

            int weight = server[0];
            int index = server[1];

            ans[j] = index;

            // Assign the task and calculate finishing time.
            busy.offer(new long[]{
                time + tasks[j], weight, index
            });
        }

        return ans;
    }
}