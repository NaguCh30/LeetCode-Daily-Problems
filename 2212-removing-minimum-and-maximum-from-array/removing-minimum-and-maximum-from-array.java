class Solution {
    public int minimumDeletions(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }

        int minInd = -1, maxInd = -1, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            if (nums[i] < min) {
                min = nums[i];
                minInd = i;
            }

            if (nums[i] > max) {
                max = nums[i];
                maxInd = i;
            }
        }

        int left = Math.min(minInd, maxInd);
        int right = Math.max(minInd, maxInd);

        int bothFront = right + 1;
        int bothBack = len - left;
        int mixed = left + 1 + len - right;

        return Math.min(bothFront, Math.min(bothBack, mixed));
        
    }
}