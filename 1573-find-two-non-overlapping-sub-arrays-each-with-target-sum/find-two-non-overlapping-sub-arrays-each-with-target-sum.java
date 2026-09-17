import java.util.*;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;

        // best[i] = shortest valid subarray
        // completely inside arr[0...i]
        int[] best = new int[n];

        Arrays.fill(best, INF);

        int left = 0;
        int sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            // Carry forward the best answer so far
            if (right > 0) {
                best[right] = best[right - 1];
            }

            // Found a subarray with sum = target
            if (sum == target) {

                int currentLength = right - left + 1;

                // Check if there is a previous
                // non-overlapping subarray
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(
                        answer,
                        currentLength + best[left - 1]
                    );
                }

                // This is the shortest valid subarray
                // ending at/before right
                best[right] = Math.min(
                    best[right],
                    currentLength
                );
            }
        }

        return answer == INF ? -1 : answer;
    }
}