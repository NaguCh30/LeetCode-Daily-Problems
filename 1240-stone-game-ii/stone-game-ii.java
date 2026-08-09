class Solution {

    int[][] dp;
    int[] suffix;

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n];

        suffix[n - 1] = piles[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = piles[i] + suffix[i + 1];
        }

        return solve(piles, 0, 1);
    }

    private int solve(int[] piles, int i, int M) {

        if (i >= piles.length) {
            return 0;
        }

        if (2 * M >= piles.length - i) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

        for (int X = 1; X <= 2 * M; X++) {

            int newM = Math.max(M, X);

            int current = suffix[i]
                         - solve(piles, i + X, newM);

            best = Math.max(best, current);
        }

        dp[i][M] = best;

        return best;
    }
}