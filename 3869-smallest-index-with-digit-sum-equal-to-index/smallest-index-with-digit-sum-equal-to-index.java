class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == sum(nums[i])) {
                return i;
            }
        }

        return -1;
    }

    int sum(int num) {
        int s = 0;

        while (num != 0) {
            int r = num % 10;
            s = s + r;
            num /= 10;
        }

        return s;
    }
}