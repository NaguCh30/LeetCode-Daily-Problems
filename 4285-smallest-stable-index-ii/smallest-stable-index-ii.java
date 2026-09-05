class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMin = new int[n];

        prefixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            prefixMin[i] = Math.min(nums[i], prefixMin[i + 1]);
        }

        
        int maxSoFar = nums[0];
        for (int i = 0; i < n; i++) {
            maxSoFar = Math.max(maxSoFar, nums[i]);

            if (maxSoFar - prefixMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}