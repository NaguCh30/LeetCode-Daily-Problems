class Solution {
    public int maxProfit(int[] prices) {
        int i = 0, j = 1, profit = 0;
        while (j < prices.length) {
            if (prices[j] > prices[i]) {
                profit += prices[j] - prices[i];
            }
            i = j;
            j++;
        }
    
        return profit;
    }  
}