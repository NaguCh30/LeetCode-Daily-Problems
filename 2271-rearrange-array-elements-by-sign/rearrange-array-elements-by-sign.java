class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int i = 0, j = 1;
        int[] result = new int[n];

        for (int k = 0; k < n; k++) {
            if (nums[k] > 0) {
                result[i] = nums[k];
                i += 2;
            } else {
                result[j] = nums[k];
                j += 2;
            }
        }

        return result;
    }
}