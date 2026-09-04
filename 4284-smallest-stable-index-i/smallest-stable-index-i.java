class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] min = new int[n];

        min[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            min[i] = Math.min(nums[i], min[i + 1]);
        }

        int maxSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxSoFar = Math.max(nums[i], maxSoFar);
            
            int instability = maxSoFar - min[i];
            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}