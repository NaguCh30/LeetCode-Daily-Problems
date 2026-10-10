
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2,
                                 int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];

        long k = (long) k1 + k2;
        int maxDiff = 0;
        long totalDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            totalDiff += diff[i];
        }

        if (totalDiff <= k) {
            return 0;
        }

        int low = 0, high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long operations = 0;

            for (int d : diff) {
                if (d > mid) {
                    operations += d - mid;
                }
            }

            if (operations <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int threshold = low;
        long used = 0;
        long answer = 0;
        long countAbove = 0;

        for (int d : diff) {
            int reduced = Math.min(d, threshold);
            answer += (long) reduced * reduced;

            if (d > threshold) {
                used += d - threshold;
                countAbove++;
            }
        }

        long remaining = k - used;

        answer -= remaining * (2L * threshold - 1);

        return answer;
    }
}
