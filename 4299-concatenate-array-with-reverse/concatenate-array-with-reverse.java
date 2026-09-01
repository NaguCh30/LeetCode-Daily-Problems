class Solution {
    public int[] concatWithReverse(int[] nums) {
        
        int len = nums.length;
        int[] result = new int[len + len];

        for (int i = 0; i < len; i++) {
            int num = nums[i];
            result[i] = num;
            result[len + len - i - 1] = num;
        }

        return result;
    }
}