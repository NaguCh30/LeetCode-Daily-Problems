class Solution {
    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;
        long[] diff = new long[n + 1];

        long active = 0;

        for (int i = 0; i < n; i++) {
            active += diff[i];

            long current = nums[i] - active;

            if (current < 0) {
                return false;
            }

            if (current > 0) {
                if (i + k > n) {
                    return false;
                }

                active += current;
                diff[i + k] -= current;
            }
        }

        return true;
    }
}