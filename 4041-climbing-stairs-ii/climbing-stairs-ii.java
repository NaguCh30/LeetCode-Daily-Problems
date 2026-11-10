class Solution {
    public int climbStairs(int n, int[] costs) {

        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            for (int jump = 1; jump <= 3; jump++) {

                int prev = i - jump;

                if (prev >= 0 && dp[prev] != Integer.MAX_VALUE) {

                    int currentCost = costs[i - 1] + jump * jump;

                    dp[i] = Math.min(dp[i],
                                     dp[prev] + currentCost);
                }
            }
        }

        return dp[n];
    }
}
