class Solution {
    public int maxProfit(int[] prices) {
        int buyAt = prices[0];
        int maxProfit = 0;

        for (int num : prices) {
            if (num > buyAt) {
                maxProfit = Math.max(maxProfit, num - buyAt);
            }
            if (num < buyAt) {
                buyAt = num;
            }
        }

        return maxProfit;
    }
}