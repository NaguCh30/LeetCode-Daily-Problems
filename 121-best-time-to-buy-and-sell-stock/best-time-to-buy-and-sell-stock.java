class Solution {
    public int maxProfit(int[] prices) {
        int buyAt = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - buyAt);

            if (prices[i] < buyAt) {
                buyAt = prices[i];
            }
        }

        return maxProfit;
    }
}