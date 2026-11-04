
import java.util.*;

class Solution {

    class MedianWindow {
        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        long lowSum = 0;
        long highSum = 0;

        int lowSize = 0;
        int highSize = 0;

        void addToMap(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        void removeFromMap(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);

            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }

        void add(int val) {
            if (lowSize == 0 || val <= low.lastKey()) {
                addToMap(low, val);
                lowSum += val;
                lowSize++;
            } else {
                addToMap(high, val);
                highSum += val;
                highSize++;
            }

            balance();
        }

        void remove(int val) {
            if (low.containsKey(val)) {
                removeFromMap(low, val);
                lowSum -= val;
                lowSize--;
            } else {
                removeFromMap(high, val);
                highSum -= val;
                highSize--;
            }

            balance();
        }

        void balance() {

            // low must contain the extra element when
            // the window size is odd.
            int target = (lowSize + highSize + 1) / 2;

            while (lowSize > target) {
                int val = low.lastKey();

                removeFromMap(low, val);
                lowSum -= val;
                lowSize--;

                addToMap(high, val);
                highSum += val;
                highSize++;
            }

            while (lowSize < target) {
                int val = high.firstKey();

                removeFromMap(high, val);
                highSum -= val;
                highSize--;

                addToMap(low, val);
                lowSum += val;
                lowSize++;
            }
        }

        long getCost() {
            long median = low.lastKey();

            long leftCost = median * lowSize - lowSum;
            long rightCost = highSum - median * highSize;

            return leftCost + rightCost;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        if ((long) x * k > n) {
            return -1;
        }

        // Step 1: Calculate the cost of every window of size x.

        int windowCount = n - x + 1;
        long[] cost = new long[windowCount];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        for (int i = 0; i < windowCount; i++) {

            cost[i] = window.getCost();

            if (i < windowCount - 1) {
                window.remove(nums[i]);
                window.add(nums[i + x]);
            }
        }

        // Step 2: Dynamic Programming.

        long INF = Long.MAX_VALUE / 4;

        // prev[i] = minimum cost of selecting
        // (j - 1) subarrays from first i elements.

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        // Selecting zero subarrays costs zero.
        Arrays.fill(prev, 0);

        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Choice 1: Skip the current element.
                curr[i] = curr[i - 1];

                // Choice 2: Select a window ending at i - 1.
                if (i >= x && prev[i - x] != INF) {

                    long take = prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], take);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
