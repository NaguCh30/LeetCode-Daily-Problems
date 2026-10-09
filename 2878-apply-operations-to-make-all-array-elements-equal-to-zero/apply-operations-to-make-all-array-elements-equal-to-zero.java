class Solution {
    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;

        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < 0) {
                return false;
            }

            if (nums[i] == 0) {
                continue;
            }

            if (i - k + 1 < 0) {
                return false;
            }

            int operations = nums[i];

            for (int j = i; j >= i - k + 1; j--) {
                nums[j] -= operations;
            }
        }

        return true;
    }
}