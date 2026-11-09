
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        // Available servers
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Busy servers: {finishingTime, serverId}
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        // Number of requests handled by each server
        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {

            int currentTime = arrival[i];

            // Release servers whose requests are completed
            while (!busy.isEmpty() && busy.peek()[0] <= currentTime) {
                int[] server = busy.poll();
                available.add(server[1]);
            }

            // All servers are busy
            if (available.isEmpty()) {
                continue;
            }

            // Preferred server
            int preferred = i % k;

            // Find next available server
            Integer serverId = available.ceiling(preferred);

            // Wrap around if necessary
            if (serverId == null) {
                serverId = available.first();
            }

            // Assign request
            available.remove(serverId);

            count[serverId]++;

            // Calculate finishing time
            int finishTime = currentTime + load[i];

            busy.offer(new int[]{finishTime, serverId});
        }

        // Find maximum requests handled
        int maxCount = 0;

        for (int c : count) {
            maxCount = Math.max(maxCount, c);
        }

        // Collect busiest servers
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxCount) {
                result.add(i);
            }
        }

        return result;
    }
}
