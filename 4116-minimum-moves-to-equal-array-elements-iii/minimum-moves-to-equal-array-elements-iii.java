class Solution {
    public int minMoves(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) max = (num > max) ? num : max;
        int tot = 0;
        for (int num : nums) tot += max - num;
        return tot;
    }
}