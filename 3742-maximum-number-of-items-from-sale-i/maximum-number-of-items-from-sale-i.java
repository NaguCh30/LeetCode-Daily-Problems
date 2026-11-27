class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        int[] dp = new int[budget + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            minPrice = Math.min(minPrice, price);

            // Calculate the benefit of buying the first copy
            int gain = 0;

            for (int[] other : items) {
                if (other[0] % factor == 0) {
                    gain++;
                }
            }

            // 0/1 Knapsack: buy the first copy only once
            for (int j = budget; j >= price; j--) {
                dp[j] = Math.max(
                    dp[j],
                    dp[j - price] + gain
                );
            }
        }

        // Buy additional copies of the cheapest item
        int ans = 0;

        for (int spent = 0; spent <= budget; spent++) {

            int remaining = budget - spent;

            int total = dp[spent] + remaining / minPrice;

            ans = Math.max(ans, total);
        }

        return ans;
    }
}
