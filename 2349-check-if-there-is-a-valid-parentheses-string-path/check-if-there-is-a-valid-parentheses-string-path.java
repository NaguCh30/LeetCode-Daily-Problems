class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Length of every possible path
        int length = m + n - 1;

        // Valid parentheses string must have even length
        if (length % 2 == 1) {
            return false;
        }

        // First character must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        boolean[][][] dp = new boolean[m][n][length + 1];

        // Starting cell
        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                for (int balance = 0; balance <= length; balance++) {

                    if (!dp[i][j][balance]) {
                        continue;
                    }

                    // Move Down
                    if (i + 1 < m) {

                        int newBalance = balance;

                        if (grid[i + 1][j] == '(') {
                            newBalance++;
                        } else {
                            newBalance--;
                        }

                        if (newBalance >= 0) {
                            dp[i + 1][j][newBalance] = true;
                        }
                    }

                    // Move Right
                    if (j + 1 < n) {

                        int newBalance = balance;

                        if (grid[i][j + 1] == '(') {
                            newBalance++;
                        } else {
                            newBalance--;
                        }

                        if (newBalance >= 0) {
                            dp[i][j + 1][newBalance] = true;
                        }
                    }
                }
            }
        }

        // At the end, balance must be exactly 0
        return dp[m - 1][n - 1][0];
    }
}