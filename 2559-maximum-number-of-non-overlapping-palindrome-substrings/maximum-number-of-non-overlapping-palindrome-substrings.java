class Solution {
    public int maxPalindromes(String s, int k) {

        int n = s.length();

        int[] dp = new int[n + 1];

        boolean[][] isPalindrome = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {

                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || isPalindrome[i + 1][j - 1])) {

                    isPalindrome[i][j] = true;
                }
            }
        }

        for (int i = 1; i <= n; i++) {

            dp[i] = dp[i - 1];

            for (int start = 0; start <= i - k; start++) {

                if (isPalindrome[start][i - 1]) {
                    dp[i] = Math.max(
                        dp[i],
                        dp[start] + 1
                    );
                }
            }
        }

        return dp[n];
    }
}