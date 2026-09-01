class Solution {
    public int[] concatWithReverse(int[] nums) {
        
        int len = nums.length;
        int newLen = len + len;
        int[] result = new int[newLen];

        for (int i = 0; i < len; i++) {
            int num = nums[i];
            result[i] = num;
            result[newLen - i - 1] = num;
        }

        return result;
    }
}